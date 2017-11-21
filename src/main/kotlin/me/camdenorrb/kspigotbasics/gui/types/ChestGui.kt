package me.camdenorrb.kspigotbasics.gui.types

import me.camdenorrb.kspigotbasics.gui.Gui
import me.camdenorrb.kspigotbasics.gui.slot.ChestSlot
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack


abstract class ChestGui(title: String, size: Int = 27): ListeningModule(), Gui {

	val chestSlots = mutableMapOf<Int, ChestSlot>()

	val inventory = server.createInventory(null, size, title)!!


	override final fun onStart() = construct()

	override final fun onStop() = deconstruct()


	override fun open(player: Player) {
		player.openInventory(inventory)
	}


	fun Material.setSlots(slots: IntRange) = slots.forEach { setSlot(it) }

	fun ItemStack.setSlots(slots: IntRange) = slots.forEach { setSlot(it) }


	fun Material.setSlot(slot: Int): ChestSlot {
		return ItemStack(this).setSlot(slot)
	}

	fun ItemStack.setSlot(slot: Int): ChestSlot {
		inventory.setItem(slot, this)
		return ChestSlot(this, slot).also { chestSlots.put(it.slot, it) }
	}


	fun remSlot(slot: Int): ItemStack? {
		inventory.clear(slot)
		return chestSlots.remove(slot)?.item
	}

	fun remSlots(slots: IntRange) = slots.forEach {
		inventory.clear(it)
		chestSlots.remove(it)
	}


	@EventHandler
	fun onClick(event: InventoryClickEvent) {

		if (event.clickedInventory != inventory) return
		event.isCancelled = true

		val action = chestSlots[event.slot]?.actions?.get(event.click) ?: return
		action.invoke(event)

	}

}