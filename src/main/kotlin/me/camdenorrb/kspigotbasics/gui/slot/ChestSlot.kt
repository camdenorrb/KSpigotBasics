package me.camdenorrb.kspigotbasics.gui.slot

import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.ClickType.LEFT
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer


private typealias Action = InventoryClickEvent.() -> Unit


class ChestSlot internal constructor(val item: ItemStack, val slot: Int) {

	val actions = mutableMapOf<ClickType, Action>()


	@JvmSynthetic
	fun addAction(clickType: ClickType = LEFT, action: Action)
		= actions.put(clickType, action)

	@JvmOverloads
	fun remAction(clickType: ClickType = LEFT)
		= actions.remove(clickType)


	@JvmOverloads
	fun addAction(clickType: ClickType = LEFT, consumer: Consumer<InventoryClickEvent>) = addAction(clickType) {
		consumer.accept(this)
	}

}