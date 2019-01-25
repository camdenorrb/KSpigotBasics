package me.camdenorrb.kspigotbasics.types.modules

import me.camdenorrb.kdi.ext.inject
import me.camdenorrb.kspigotbasics.KSpigotBasics
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.minibus.MiniBus
import me.camdenorrb.minibus.listener.MiniListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


abstract class ListeningModule(val plugin: JavaPlugin = inject<KSpigotBasics>(), val miniBus: MiniBus = inject()) : ModuleImpl(), Listener, MiniListener {

	protected open fun onStart() = Unit

	protected open fun onStop() = Unit


	final override fun onEnable() {
		onStart()
		miniBus.register(this)
		server.pluginManager.registerEvents(this, plugin)
	}

	final override fun onDisable() {
		onStop()
		miniBus.unregister(this)
		HandlerList.unregisterAll(this)
	}

}