package me.camdenorrb.kspigotbasics.gui.actions

import me.camdenorrb.kspigotbasics.gui.Gui
import me.camdenorrb.kspigotbasics.gui.types.ChestGui
import me.camdenorrb.kspigotbasics.struct.scheduler
import me.camdenorrb.kspigotbasics.struct.spigotBasics
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory


class ChestClickAction(val clickedGui: ChestGui, val clickEvent: InventoryClickEvent) {

	var isCancelled: Boolean
		get() = clickEvent.isCancelled
		set(value) { clickEvent.isCancelled = value }

	var result: Event.Result
		get() = clickEvent.result
		set(value) { clickEvent.result = value }


	val slot by lazy { clickEvent.slot }

	val rawSlot by lazy { clickEvent.rawSlot }

	val clicked by lazy { clickEvent.currentItem }


	val isLeftClick by lazy { clickEvent.isLeftClick }

	val isRightClick by lazy { clickEvent.isRightClick }

	val isShiftClick by lazy { clickEvent.isShiftClick }


	val click by lazy { clickEvent.click!! }

	val cursor by lazy { clickEvent.cursor!! }


	val viewers by lazy { clickEvent.viewers!! }

	val whoClicked by lazy { clickEvent.whoClicked as Player }


	val hotbarButton by lazy { clickEvent.hotbarButton }

	val clickedInventory by lazy { clickEvent.clickedInventory }



	// Safety precautions, as stated in the InventoryClickEvent JavaDocs.

	fun switchGui(gui: Gui) = scheduler.runTask(spigotBasics) {
		gui.open(whoClicked)
	}!!

	fun closeGui() = scheduler.runTask(spigotBasics) {
		whoClicked.closeInventory()
	}!!

	fun switchInventory(inventory: Inventory) = scheduler.runTask(spigotBasics) {
		whoClicked.openInventory(inventory)
	}!!


	@JvmOverloads
	fun openWorkbench(location: Location, force: Boolean = false) = scheduler.runTask(spigotBasics) {
		whoClicked.openWorkbench(location, force)
	}!!

}