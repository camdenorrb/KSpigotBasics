package me.camdenorrb.kspigotbasics

import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import org.bukkit.plugin.java.JavaPlugin


lateinit var spigotBasics: KSpigotBasics private set

class KSpigotBasics : JavaPlugin() {

	override fun onLoad() { spigotBasics = this }

	override fun onEnable() { KBasicPlayerCache.enable() }

	override fun onDisable() { KBasicPlayerCache.disable() }

}