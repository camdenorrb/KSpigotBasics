package me.camdenorrb.kspigotbasics.listeners

import me.camdenorrb.kdi.ext.inject
import me.camdenorrb.kspigotbasics.events.PlayerMoveBlockEvent
import me.camdenorrb.minibus.MiniBus
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent


class CoreListener(val miniBus: MiniBus = inject()) : Listener {

	@kotlin.ExperimentalStdlibApi
	@EventHandler(ignoreCancelled = true)
	fun PlayerMoveEvent.onBlockMove() {

		if (from.block == to.block) return

		val result = miniBus.invoke(PlayerMoveBlockEvent(player, from, to))
		isCancelled = result.isCancelled
	}

}