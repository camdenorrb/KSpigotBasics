package me.camdenorrb.kspigotbasics.conversation.impl

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.camdenorrb.kdi.ext.inject
import me.camdenorrb.kspigotbasics.KSpigotBasics
import me.camdenorrb.kspigotbasics.conversation.type.IConversation
import me.camdenorrb.kspigotbasics.events.wrappers.PlayerBlockPlaceEvent
import me.camdenorrb.kspigotbasics.struct.RED
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.utils.listen
import me.camdenorrb.kspigotbasics.utils.playerListen
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class Conversation(override val target: Player, val plugin: JavaPlugin = inject<KSpigotBasics>(), private val block: suspend Conversation.() -> Unit) : IConversation<Player>() {

	private lateinit var job: Job


	override fun onStart() {
		job = GlobalScope.launch { block() }
	}

	override fun onStop() {
		job.cancel()
	}


	fun Conversation.sendMsg(message: String) {
		target.sendMessage(message)
	}



	suspend fun Conversation.waitForBlockPlace(cancel: Boolean = true): PlayerBlockPlaceEvent {
		return waitForTargetEvent(cancel)
	}

	suspend fun Conversation.waitForReply(): String {
		return waitForTargetEvent<AsyncPlayerChatEvent>().message
	}

	suspend fun Conversation.waitForNumber(): Int {
		return waitForNotNull("number") {
			it.toIntOrNull()
		}
	}

	suspend fun Conversation.waitForDecimal(): Double {
		return waitForNotNull("decimal") {
			it.toDoubleOrNull()
		}
	}

	suspend fun <T> Conversation.waitForNotNull(typeName: String, block: (String) -> T?): T {
		return waitForNotNull({ "${RED}Invalid input, please input a valid $typeName." }, block)
	}

	suspend fun <T> Conversation.waitForNotNull(failMessage: () -> String, block: (String) -> T?): T {

		var result: T? = null

		while (result == null) {
			result = block(waitForReply())
			if (result == null) sendMsg(failMessage())
		}

		return result
	}


	// Waits for an event
	suspend inline fun <reified E : Event> Conversation.waitForEvent() = suspendCoroutine<E> { cont ->
		server.scheduler.runTask(plugin, Runnable {
			listen<E> {
				cont.resume(it)
				HandlerList.unregisterAll(this)
			}
		})
	}

	// Waits for an event caused by the target, then it cancels it and continues
 	suspend inline fun <reified E : PlayerEvent> Conversation.waitForTargetEvent(cancel: Boolean = true) = suspendCoroutine<E> { cont ->
		server.scheduler.runTask(plugin, Runnable {
			playerListen<E>(target) {

				if (cancel && it is Cancellable) {
					it.isCancelled = true
				}

				cont.resume(it)
				HandlerList.unregisterAll(this)
			}
		})
	}

}