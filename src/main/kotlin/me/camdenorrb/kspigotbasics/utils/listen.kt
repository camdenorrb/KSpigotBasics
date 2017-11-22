@file:JvmName("ListenUtils")
@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.spigotBasics
import me.camdenorrb.kspigotbasics.struct.pluginManager
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.EventPriority.NORMAL
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor
import java.util.function.BiConsumer


@JvmOverloads
fun <E : Event> listen(eventClazz: Class<out E>, priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, consumer: BiConsumer<Listener, E>): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let { consumer.accept(this, it) } ?: Unit

	}

	pluginManager.registerEvent(eventClazz, listener, priority, listener, spigotBasics, ignoreCancelled)
	return listener

}

@JvmSynthetic
inline fun <reified E : Event> listen(priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, noinline block: Listener.(E) -> Unit): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let { block(this, it) } ?: Unit

	}

	pluginManager.registerEvent(E::class.java, listener, priority, listener, spigotBasics, ignoreCancelled)
	return listener
}