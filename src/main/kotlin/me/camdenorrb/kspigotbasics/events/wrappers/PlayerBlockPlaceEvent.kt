package me.camdenorrb.kspigotbasics.events.wrappers

import org.bukkit.block.Block
import org.bukkit.block.BlockState
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.inventory.ItemStack

class PlayerBlockPlaceEvent(private val event: BlockPlaceEvent) : PlayerEvent(event.player), Cancellable {

	val blockPlaced: Block
		get() = event.blockPlaced

	val itemInHand: ItemStack
		get() = event.itemInHand

	val blockAgainst: Block
		get() = event.blockAgainst

	val blockReplacedState: BlockState
		get() = event.blockReplacedState


	var canBuild: Boolean
		get() = event.canBuild()
		set(value) = event.setBuild(value)


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