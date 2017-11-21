package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.struct.pluginManager
import me.camdenorrb.kspigotbasics.struct.spigotBasics
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor


inline fun <reified E : Event> listen(priority: EventPriority = EventPriority.NORMAL, ignoreCancelled: Boolean = true, noinline block: Listener.(E) -> Unit): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let { block(this, it) } ?: Unit

	}

	pluginManager.registerEvent(E::class.java, listener, priority, listener, spigotBasics, ignoreCancelled)
	return listener
}