package me.camdenorrb.kspigotbasics.listeners

import me.camdenorrb.kspigotbasics.events.wrappers.PlayerBlockBreakEvent
import me.camdenorrb.kspigotbasics.events.wrappers.PlayerBlockPlaceEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class WrapperListener : Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	fun onBlockPlace(event: BlockPlaceEvent) {
		Bukkit.getPluginManager().callEvent(PlayerBlockPlaceEvent(event))
	}

	@EventHandler(priority = EventPriority.LOWEST)
	fun onBlockBreak(event: BlockBreakEvent) {
		Bukkit.getPluginManager().callEvent(PlayerBlockBreakEvent(event))
	}

}