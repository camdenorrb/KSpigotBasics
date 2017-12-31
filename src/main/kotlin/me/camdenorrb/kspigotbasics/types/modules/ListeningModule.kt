package me.camdenorrb.kspigotbasics.types.modules

import me.camdenorrb.kspigotbasics.struct.miniBus
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.struct.spigotBasics
import me.camdenorrb.minibus.listener.MiniListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener


abstract class ListeningModule : ModuleImpl(), Listener, MiniListener {

	protected open fun onStart() = Unit

	protected open fun onStop() = Unit


	override final fun onEnable() {
		onStart()
		miniBus.register(this)
		server.pluginManager.registerEvents(this, spigotBasics)
	}

	override final fun onDisable() {
		onStop()
		miniBus.unregister(this)
		HandlerList.unregisterAll(this)
	}

}