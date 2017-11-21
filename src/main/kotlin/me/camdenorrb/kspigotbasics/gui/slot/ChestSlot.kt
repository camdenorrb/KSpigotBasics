package me.camdenorrb.kspigotbasics.gui.slot

import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.ClickType.LEFT
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack


private typealias Action = InventoryClickEvent.() -> Unit


class ChestSlot internal constructor(val item: ItemStack, val slot: Int) {

	val actions = mutableMapOf<ClickType, Action>()


	fun addAction(clickType: ClickType = LEFT, action: Action)
		= actions.put(clickType, action)

	fun remAction(clickType: ClickType = LEFT)
		= actions.remove(clickType)

}