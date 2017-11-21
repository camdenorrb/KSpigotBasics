package me.camdenorrb.kspigotbasics

import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import org.bukkit.plugin.java.JavaPlugin


class KSpigotBasics : JavaPlugin() {

	override fun onLoad() { instance = this }

	override fun onEnable() { KBasicPlayerCache.enable() }

	override fun onDisable() { KBasicPlayerCache.disable() }

	
	companion object {

		lateinit var instance: KSpigotBasics
			private set

	}

}