package me.camdenorrb.kspigotbasics.listeners

import me.camdenorrb.kspigotbasics.events.PlayerMoveBlockEvent
import me.camdenorrb.kspigotbasics.struct.miniBus
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent


class CoreListener : Listener {

	@EventHandler(ignoreCancelled = true)
	fun PlayerMoveEvent.onBlockMove() {

		if (from.block == to.block) return

		val result = miniBus(PlayerMoveBlockEvent(player, from, to))
		isCancelled = result.isCancelled
	}

}