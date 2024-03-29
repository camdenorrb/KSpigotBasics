package me.camdenorrb.kspigotbasics.scoreboard

import me.camdenorrb.kspigotbasics.scoreboard.ext.contains
import me.camdenorrb.kspigotbasics.struct.RESET
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.Openable
import me.camdenorrb.kspigotbasics.utils.listen
import me.camdenorrb.kspigotbasics.utils.playerListen
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerEvent
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team
import java.util.*
import java.util.function.Consumer


// TODO: Make a inlined scoreboard, which will attribute to the player's current scoreboard or make a new one if main.
open class Board : Openable<Player> {

	val viewers = mutableListOf<UUID>()

	var onOpenBlock: (Player) -> Unit = {}
	var onCloseBlock: (Player) -> Unit = {}

	internal val scoreboard = server.scoreboardManager!!.newScoreboard


	var sideBar: SideBar? = null
		private set


	val teams: MutableSet<Team>
		get() = scoreboard.teams

	val objectives: MutableSet<Objective>
		get() = scoreboard.objectives


	operator fun get(objName: String): Objective? = scoreboard.getObjective(objName)

	operator fun get(displaySlot: DisplaySlot): Objective? = scoreboard.getObjective(displaySlot)


	override fun open(target: Player) {
		onOpen(target); onOpenBlock(target)
		viewers += target.uniqueId
		target.scoreboard = scoreboard
	}

	override fun close(target: Player) {
		close(target, Bukkit.getScoreboardManager()!!.mainScoreboard)
	}

	fun close(target: Player, newBoard: Scoreboard) {
		onClose(target); onCloseBlock(target)
		viewers -= target.uniqueId
		target.scoreboard = newBoard
	}


	fun onOpen(target: Player) = Unit

	fun onClose(target: Player) = Unit


	fun findTeam(player: Player): Team? {
		return scoreboard.teams.find { player in it }
	}

	fun findTeam(name: String, ignoreCase: Boolean = false): Team? {
		return scoreboard.teams.find { it.name.equals(name, ignoreCase) }
	}


	fun createTeam(name: String): Team {
		return scoreboard.registerNewTeam(name)!!
	}

	@JvmSynthetic
	fun createTeam(name: String, build: Team.() -> Unit): Team {
		return createTeam(name).also(build)
	}


	fun createSideBar(name: String): SideBar {
		sideBar?.unregister()
		return SideBar(name).also { sideBar = it }
	}

	@JvmSynthetic
	fun createSideBar(name: String, size: Int, builder: SideBarBuilder.() -> Unit = {}): SideBar {

		sideBar?.unregister()
		sideBar = SideBarBuilder(name, size).also(builder).sideBar

		return sideBar!!
	}

	@JvmSynthetic
	fun createObj(name: String, criteria: String = "dummy", build: Objective.() -> Unit = {}): Objective {
		return scoreboard.registerNewObjective(name, criteria).also(build)
	}


	fun createTeam(name: String, consumer: Consumer<Team>) = createTeam(name) {
		consumer.accept(this)
	}

	fun createSideBar(name: String, size: Int, consumer: Consumer<SideBarBuilder>) = createSideBar(name, size) {
		consumer.accept(this)
	}

	@JvmOverloads
	@JvmName("createObj")
	fun jCreateObj(name: String, criteria: String = "dummy", consumer: Consumer<Objective>) = createObj(name, criteria) {
		consumer.accept(this)
	}


	inner class SideBar internal constructor(val name: String) {

		private lateinit var sideTeams: List<Team>


		val listeners = mutableListOf<Listener>()


		var isRegistered = false
			private set


		lateinit var objective: Objective
			private set


		var displayName: String
			get() = objective.displayName
			set(value) { objective.displayName = value }


		fun register() {

			if (isRegistered) return


			objective = createObj(name, "dummy") { displaySlot = DisplaySlot.SIDEBAR }

			sideTeams = ChatColor.values().map {
				createTeam("$it").apply { addEntry("$it$RESET") }
			}

			sideBar = this
			isRegistered = true
		}


		fun unregister() {

			if (!isRegistered) return

			objective.unregister()
			sideTeams.forEach { it.unregister() }

			sideBar = null
			isRegistered = false

			listeners.forEach { HandlerList.unregisterAll(it) }
		}


		operator fun get(index: Int): String {
			check(index in 0..14) { "Tried to get a entry out of range!" }
			return sideTeams[index].let { "${it.prefix}${it.suffix}" }
		}

		operator fun set(index: Int, value: String) {

			check(index in 0..14) { "Tried to set a entry out of range!" }

			val length = value.length

			val team = sideTeams[index]
			team.prefix = value.substring(0 until length.coerceAtMost(16))
			team.suffix = if (length > 16) "${ChatColor.getLastColors(team.prefix)}${value.substring(16 until length.coerceAtMost(32))}" else ""

			objective.getScore("${team.name}$RESET").score = index
		}

	}


	inner class SideBarBuilder internal constructor(val name: String, val size: Int) {

		var currentIndex = 0
			private set


		val realSize = size - 1

		val sideBar = SideBar(name).apply { register() }


		var displayName: String
			get() = sideBar.displayName
			set(value) { sideBar.displayName = value }


		init {
			check(realSize in 0..13) { "Size for SideBarBuilder is too large!" }
			for (i in 0..realSize) sideBar[i] = ""
		}


		fun emptyLine() = text("")


		fun text(vararg text: String) {
			text.forEach(this::text)
		}

		fun text(text: String) {
			check(currentIndex in 0..realSize) { "Tried to add a entry out of range!" }
			sideBar[realSize - currentIndex++] = text
		}


		// TODO: Better Java support.
		// TODO: Possibly set a default prefix and default value instead of the text itself. It can be 2 functions if needed.
		
		@JvmSynthetic
		inline fun <reified E : Event> listeningText(initial: String = "", noinline block: E.() -> String) {

			check(currentIndex in 0..realSize) { "Tried to add a entry out of range!" }

			val textIndex = realSize - currentIndex

			text(initial)

			sideBar.listeners += listen<E> {
				if (!sideBar.isRegistered) HandlerList.unregisterAll(this)
				sideBar[textIndex] = block(it)
			}

		}


		
		@JvmSynthetic
		inline fun <reified E : PlayerEvent> playerListeningText(player: Player, initial: String = "", noinline block: E.() -> String) {

			check(currentIndex in 0..realSize) { "Tried to add a entry out of range!" }

			val textIndex = realSize - currentIndex

			text(initial)

			sideBar.listeners += playerListen<E>(player) {
				if (!sideBar.isRegistered) HandlerList.unregisterAll(this)
				sideBar[textIndex] = block(it)
			}

		}

	}

}