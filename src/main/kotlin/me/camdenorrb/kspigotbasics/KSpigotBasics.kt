package me.camdenorrb.kspigotbasics

import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import me.camdenorrb.kspigotbasics.cache.ReflectCache
import me.camdenorrb.kspigotbasics.ext.register
import me.camdenorrb.kspigotbasics.listeners.CoreListener
import me.camdenorrb.kspigotbasics.struct.miniBus
import org.bukkit.plugin.java.JavaPlugin


class KSpigotBasics : JavaPlugin() {

	override fun onLoad() { instance = this }

	override fun onEnable() {
		register(CoreListener())
		modules.forEach { it.enable() }
	}

	override fun onDisable() {
		miniBus.cleanUp()
		modules.forEach { it.disable() }
	}


	companion object {

		val modules = arrayOf(ReflectCache, KBasicPlayerCache) //DisguiseCache

		lateinit var instance: KSpigotBasics
			private set

	}

}