package me.camdenorrb.kspigotbasics.gui.slot

import me.camdenorrb.kspigotbasics.gui.events.ChestClickAction
import me.camdenorrb.kspigotbasics.struct.ChestClickBlock
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.ClickType.LEFT
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer


class ChestSlot internal constructor(val item: ItemStack, val slot: Int) {

	val actions = mutableMapOf<ClickType, ChestClickBlock>()


	@JvmSynthetic
	fun addAction(clickType: ClickType = LEFT, action: ChestClickBlock)
		= actions.put(clickType, action)

	@JvmOverloads
	fun remAction(clickType: ClickType = LEFT)
		= actions.remove(clickType)


	@JvmOverloads
	fun addAction(clickType: ClickType = LEFT, consumer: Consumer<ChestClickAction>) = addAction(clickType) {
		consumer.accept(this)
	}

}