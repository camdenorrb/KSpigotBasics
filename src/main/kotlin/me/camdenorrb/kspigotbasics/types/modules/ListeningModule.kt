package me.camdenorrb.kspigotbasics.types.modules

import me.camdenorrb.kspigotbasics.spigotBasics
import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener


abstract class ListeningModule : ModuleImpl(), Listener {

	protected open fun onStart() = Unit

	protected open fun onStop() = Unit


	override final fun onEnable() {
		onStart()
		server.pluginManager.registerEvents(this, spigotBasics)
	}

	override final fun onDisable() {
		onStop()
		HandlerList.unregisterAll(this)
	}

}