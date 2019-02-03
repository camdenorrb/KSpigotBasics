package me.camdenorrb.kspigotbasics.ext

import me.camdenorrb.kspigotbasics.struct.pluginManager
import org.bukkit.event.Event

/**
 * Clean version independent way to call events
 */
fun Event.call() {
	pluginManager.callEvent(this)
}