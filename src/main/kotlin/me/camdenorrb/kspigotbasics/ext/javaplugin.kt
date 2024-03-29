@file:JvmName("JavaPluginExt")

package me.camdenorrb.kspigotbasics.ext

import me.camdenorrb.kdi.ext.inject
import me.camdenorrb.kspigotbasics.cmd.SCmdInfo
import me.camdenorrb.kspigotbasics.struct.bungeeMessenger
import me.camdenorrb.kspigotbasics.struct.pluginManager
import me.camdenorrb.minibus.MiniBus
import me.camdenorrb.minibus.listener.MiniListener
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener


// TODO: Make a JavaPlugin wrapper

fun JavaPlugin.register(miniBus: MiniBus = inject(), vararg listeners: MiniListener) = listeners.forEach {
	miniBus.register(it)
}

fun JavaPlugin.register(vararg listeners: Listener) = listeners.forEach {
	pluginManager.registerEvents(it, this)
}

fun JavaPlugin.register(vararg cmds: SCmdInfo) = cmds.forEach {
	getCommand(it.name)?.setExecutor(it.executor)
}


@JvmSynthetic
fun JavaPlugin.register(vararg cmds: Pair<String, CommandExecutor>) = cmds.forEach {
	getCommand(it.first)?.setExecutor(it.second)
}


fun JavaPlugin.registerOutPluginChannel(vararg channels: String) = channels.forEach {
	bungeeMessenger.registerOutgoingPluginChannel(this, it)
}

fun JavaPlugin.registerInPluginChannel(channel: String, listener: PluginMessageListener) {
	bungeeMessenger.registerIncomingPluginChannel(this, channel, listener)
}
