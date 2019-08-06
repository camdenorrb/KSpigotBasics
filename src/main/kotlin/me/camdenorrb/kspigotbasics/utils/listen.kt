@file:JvmName("ListenUtils")
@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kdi.ext.inject
import me.camdenorrb.kspigotbasics.KSpigotBasics
import me.camdenorrb.kspigotbasics.struct.pluginManager
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.EventPriority.NORMAL
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerEvent
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.function.BiConsumer


@JvmOverloads
@JvmName("listen")

fun <E : Event> JListen(eventClazz: Class<out E>, priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, plugin: JavaPlugin = inject<KSpigotBasics>(), consumer: BiConsumer<Listener, E>): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let { consumer.accept(this, it) } ?: Unit

	}

	pluginManager.registerEvent(eventClazz, listener, priority, listener, plugin, ignoreCancelled)
	return listener
}

@JvmSynthetic

inline fun <reified E : Event> listen(priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, plugin: JavaPlugin = inject<KSpigotBasics>(), noinline block: Listener.(E) -> Unit): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let { block(this, it) } ?: Unit

	}

	pluginManager.registerEvent(E::class.java, listener, priority, listener, plugin, ignoreCancelled)
	return listener
}



@JvmSynthetic
inline fun <reified E : PlayerEvent> playerListen(player: Player, priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, plugin: JavaPlugin = inject<KSpigotBasics>(), noinline block: Listener.(E) -> Unit): Listener {
	return playerListen(player.uniqueId, priority, ignoreCancelled, plugin, block)
}


@JvmSynthetic
inline fun <reified E : PlayerEvent> playerListen(playerUid: UUID, priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, plugin: JavaPlugin = inject<KSpigotBasics>(), noinline block: Listener.(E) -> Unit): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let {
			if (event.player.uniqueId == playerUid) block(this, it)
		} ?: Unit

	}

	pluginManager.registerEvent(E::class.java, listener, priority, listener, plugin, ignoreCancelled)
	return listener
}


@JvmOverloads
@JvmName("playerListen")

fun <E : PlayerEvent> jPlayerListen(player: Player, eventClazz: Class<out E>, priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, plugin: JavaPlugin = inject<KSpigotBasics>(), consumer: BiConsumer<Listener, E>): Listener {
	return jPlayerListen(player.uniqueId, eventClazz, priority, ignoreCancelled, plugin, consumer)
}

@JvmOverloads
@JvmName("playerListen")

fun <E : PlayerEvent> jPlayerListen(playerUid: UUID, eventClazz: Class<out E>, priority: EventPriority = NORMAL, ignoreCancelled: Boolean = true, plugin: JavaPlugin = inject<KSpigotBasics>(), consumer: BiConsumer<Listener, E>): Listener {

	val listener = object : Listener, EventExecutor {

		override fun execute(listener: Listener, event: Event) = (event as? E)?.let {
			if (event.player.uniqueId == playerUid) consumer.accept(this, it)
		} ?: Unit

	}

	pluginManager.registerEvent(eventClazz, listener, priority, listener, plugin, ignoreCancelled)
	return listener
}