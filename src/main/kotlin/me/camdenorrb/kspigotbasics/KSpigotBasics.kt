package me.camdenorrb.kspigotbasics

import me.camdenorrb.kdi.KDI
import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import me.camdenorrb.kspigotbasics.cache.ReflectCache
import me.camdenorrb.kspigotbasics.ext.register
import me.camdenorrb.kspigotbasics.listeners.CoreListener
import me.camdenorrb.kspigotbasics.listeners.WrapperListener
import me.camdenorrb.minibus.MiniBus
import org.bukkit.plugin.java.JavaPlugin


class KSpigotBasics : JavaPlugin() {

	val miniBus = MiniBus()

	override fun onLoad() {
		KDI.insertAll {
			producer { this }
			producer { miniBus }
		}
	}

	override fun onEnable() {
		register(CoreListener(), WrapperListener())
		modules.forEach { it.enable() }
	}

	override fun onDisable() {
		miniBus.cleanUp()
		modules.forEach { it.disable() }
	}


	companion object {

		val modules = arrayOf(ReflectCache, KBasicPlayerCache) //DisguiseCache

	}

}