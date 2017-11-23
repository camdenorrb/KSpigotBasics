package me.camdenorrb.kspigotbasics.gui.types

import me.camdenorrb.kspigotbasics.gui.Gui
import me.camdenorrb.kspigotbasics.gui.events.ChestClickAction
import me.camdenorrb.kspigotbasics.gui.slot.ChestSlot
import me.camdenorrb.kspigotbasics.struct.ChestClickBlock
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer


abstract class ChestGui(title: String, size: Int = 27): ListeningModule(), Gui {

	val chestSlots = mutableMapOf<Int, ChestSlot>()

	val inventory = server.createInventory(null, size, title)!!


	var defaultAction: ChestClickBlock = {}


	protected open fun construct() = Unit

	protected open fun deconstruct() = Unit


	override final fun onStart() = construct()

	override final fun onStop() = deconstruct()


	override fun open(target: Player) {
		if (!isEnabled) enable()
		target.openInventory(inventory)
	}


	inline fun Material.setSlots(slotRange: IntRange, block: (ChestSlot) -> Unit = {})
		= ItemStack(this).setSlots(slotRange, block)

	inline fun ItemStack.setSlots(slotRange: IntRange, block: (ChestSlot) -> Unit = {}) = slotRange.forEach {
		block(setSlot(it))
	}


	fun setDefaultAction(consumer: Consumer<ChestClickAction>) {
		defaultAction = { consumer.accept(this) }
	}


	@JvmOverloads
	fun Material.setSlots(slotRange: IntRange, consumer: Consumer<ChestSlot> = Consumer {  })
		= setSlots(slotRange) { consumer.accept(it) }

	@JvmOverloads
	fun ItemStack.setSlots(slotRange: IntRange, consumer: Consumer<ChestSlot> = Consumer {  })
		= setSlots(slotRange) { consumer.accept(it) }


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
	internal fun onClose(event: InventoryCloseEvent) {
		if (event.inventory != inventory) return
		if (inventory.viewers.isEmpty()) disable()
	}

	@EventHandler
	internal fun onClick(event: InventoryClickEvent) {

		if (event.clickedInventory != inventory) return

		event.isCancelled = true

		val chestClickAction = ChestClickAction(this, event)
		val action = chestSlots[event.slot]?.actions?.get(event.click) ?: return defaultAction(chestClickAction)

		action(chestClickAction)
	}

}