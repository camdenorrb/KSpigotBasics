package me.camdenorrb.kspigotbasics.scoreboard

import me.camdenorrb.kspigotbasics.spigotBasics
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scheduler.BukkitTask


abstract class PlayerBoard : ListeningModule() {

	val boards = mutableMapOf<Player, Board>()

	private lateinit var cleanUpTask: BukkitTask


	protected open fun onInitiate() = Unit

	protected open fun onPoison() = Unit


	protected open fun Board.onConstruct(player: Player) = Unit

	protected open fun Board.onUnload(player: Player) = Unit


	override final fun onStart() {
		cleanUpTask = server.scheduler.runTaskTimerAsynchronously(spigotBasics, ::cleanUp, 6000, 6000)
		onInitiate()
	}

	override final fun onStop() {

		if (!this::cleanUpTask.isInitialized || cleanUpTask.isCancelled) return

		onPoison()

		boards.forEach { (player, board) ->
			if (player.scoreboard != board.scoreboard)
			player.scoreboard = server.scoreboardManager.mainScoreboard
			unload(player)
		}

		cleanUpTask.cancel()
	}


	@EventHandler(ignoreCancelled = true)
	private fun onLeave(event: PlayerQuitEvent) = unload(event.player)


	operator fun get(player: Player) = boards[player]


	fun unload(player: Player) = boards.remove(player)?.onUnload(player)

	fun construct(player: Player) = Board().apply {
		onConstruct(player); boards.put(player, this)
	}


	private fun cleanUp() {
		boards.entries.filter { it.value != it.key.scoreboard }.forEach { unload(it.key) }
	}

}