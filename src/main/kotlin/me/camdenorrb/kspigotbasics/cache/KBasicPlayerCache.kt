package me.camdenorrb.kspigotbasics.cache

import me.camdenorrb.kspigotbasics.player.KBasicPlayer
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent



object KBasicPlayerCache : ListeningModule() {

	val basicPlayers = mutableMapOf<Player, KBasicPlayer>()


	override fun onStart() = server.onlinePlayers.forEach { join(it) }

	override fun onStop() = basicPlayers.clear()


	fun join(player: Player) {
		basicPlayers[player] = KBasicPlayer(player)
	}

	fun leave(player: Player) {
		basicPlayers.remove(player)
	}


	@EventHandler(ignoreCancelled = true)
	fun onJoin(event: PlayerJoinEvent) = join(event.player)

	@EventHandler(ignoreCancelled = true)
	fun onLeave(event: PlayerQuitEvent) = leave(event.player)

}