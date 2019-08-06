package me.camdenorrb.kspigotbasics.scoreboard

import me.camdenorrb.kdi.ext.inject
import me.camdenorrb.kspigotbasics.KSpigotBasics
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask


data class PlayerBoard(val player: Player) : Board() {

	var onUnloadBlock: () -> Unit = {}

	
	inline fun <reified E : PlayerEvent> SideBarBuilder.playerListeningText(initial: String = "", noinline block: E.() -> String) {
		this.playerListeningText(player, initial, block)
	}

}

abstract class PlayerBoards(plugin: JavaPlugin = inject<KSpigotBasics>()) : ListeningModule(plugin), (Player) -> Unit {

	val boards = mutableListOf<PlayerBoard>()

	private lateinit var cleanUpTask: BukkitTask


	protected open fun onInitiate() = Unit

	protected open fun onPoison() = Unit


	protected open fun PlayerBoard.onConstruct() = Unit

	protected open fun PlayerBoard.onUnload() = Unit


	override fun invoke(player: Player) = construct(player).open(player)


	final override fun onStart() {
		cleanUpTask = server.scheduler.runTaskTimerAsynchronously(plugin, ::cleanUp, 6000, 6000)
		onInitiate()
	}

	final override fun onStop() {

		// NOTE: Commented out for version support
		if (!this::cleanUpTask.isInitialized/*|| cleanUpTask.isCancelled*/) return

		onPoison()

		boards.forEach { board ->
			val player = board.player
			if (player.scoreboard != board.scoreboard) return@forEach
			player.scoreboard = server.scoreboardManager!!.mainScoreboard
			unload(player)
		}

		cleanUpTask.cancel()
	}


	@EventHandler(ignoreCancelled = true)
	private fun onLeave(event: PlayerQuitEvent) = unload(event.player)


	operator fun get(player: Player) = boards.find { it.player == player }


	fun unload(player: Player) = get(player)?.let { unload(it) }

	fun unload(playerBoard: PlayerBoard) = playerBoard.apply {
		boards.remove(this); onUnload(); onUnloadBlock()
	}

	fun construct(player: Player) = PlayerBoard(player).apply {
		onConstruct()
	}

	private fun cleanUp() {
		boards.filter { it.scoreboard != it.player.scoreboard }.forEach { unload(it) }
	}

}
