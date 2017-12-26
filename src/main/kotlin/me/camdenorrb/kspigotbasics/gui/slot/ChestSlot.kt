package me.camdenorrb.kspigotbasics.gui.slot

import me.camdenorrb.kspigotbasics.gui.actions.ChestClickAction
import me.camdenorrb.kspigotbasics.struct.ChestClickLambda
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.ClickType.LEFT
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer


class ChestSlot internal constructor(val item: ItemStack, val slot: Int) {

	val actions = mutableMapOf<ClickType, ChestClickLambda>()


	@JvmSynthetic
	fun addAction(clickType: ClickType = LEFT, action: ChestClickLambda)
		= actions.put(clickType, action)

	@JvmOverloads
	fun remAction(clickType: ClickType = LEFT)
		= actions.remove(clickType)


	@JvmOverloads
	fun addAction(clickType: ClickType = LEFT, consumer: Consumer<ChestClickAction>)
		= addAction(clickType) { consumer.accept(this) }

}