package me.camdenorrb.kspigotbasics.events.wrappers

import org.bukkit.block.Block
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerEvent

class PlayerBlockBreakEvent(private val event: BlockBreakEvent) : PlayerEvent(event.player), Cancellable {

	val block: Block
		get() = event.block

	var expDropAmount: Int
		get() = event.expToDrop
		set(value) { event.expToDrop = value }


	override fun getHandlers(): HandlerList {
		return handlerList
	}

	override fun isCancelled(): Boolean {
		return event.isCancelled
	}

	override fun setCancelled(isCancelled: Boolean) {
		event.isCancelled = isCancelled
	}


	companion object {

		@JvmStatic
		val handlerList = HandlerList()

	}

}