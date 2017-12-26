@file:JvmName("Constants")


package me.camdenorrb.kspigotbasics.struct

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.camdenorrb.kspigotbasics.gson.LocAdapt
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.messaging.Messenger
import org.bukkit.scheduler.BukkitScheduler
import java.util.concurrent.ThreadLocalRandom


val server: Server = Bukkit.getServer()

val scheduler: BukkitScheduler = server.scheduler


val bungeeMessenger: Messenger = server.messenger

val pluginManager: PluginManager = server.pluginManager


val random: ThreadLocalRandom = ThreadLocalRandom.current()


val gson: Gson = GsonBuilder()
	.setPrettyPrinting()
	.enableComplexMapKeySerialization()
	.registerTypeAdapter(Location::class.java, LocAdapt())
	.create()