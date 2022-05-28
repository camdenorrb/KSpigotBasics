package me.camdenorrb.kspigotbasics.events.wrappers

import org.bukkit.block.Block
import org.bukkit.block.BlockState
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.inventory.ItemStack

class PlayerBlockPlaceEvent(private val event: BlockPlaceEvent) : PlayerEvent(event.player), Cancellable {

	val blockPlaced by lazy {
		event.blockPlaced
	}

	val itemInHand by lazy {
		event.itemInHand
	}


	val blockAgainst by lazy {
		event.blockAgainst
	}

	val blockPlacedType by lazy {
		blockPlaced.type
	}

	val blockReplacedState by lazy {
		event.blockReplacedState
	}


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