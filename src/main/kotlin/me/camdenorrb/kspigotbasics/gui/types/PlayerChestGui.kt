package me.camdenorrb.kspigotbasics.gui.types

import me.camdenorrb.kspigotbasics.gui.Gui
import me.camdenorrb.kspigotbasics.gui.utils.chestGui
import me.camdenorrb.kspigotbasics.types.modules.Module
import org.bukkit.entity.Player


abstract class PlayerChestGui(val title: String, val size: Int = 27) : Gui, Module, (Player) -> ChestGui {

	val playerChests = mutableMapOf<Player, ChestGui>()


	protected abstract fun onConstruct(player: Player, chestGui: ChestGui)

	protected open fun onDestroy(player: Player) = Unit


	override fun open(target: Player) {
		invoke(target).open(target)
	}

	override fun invoke(player: Player): ChestGui {
		val chestGui = chestGui(title, size) { onConstruct(player, this) }
		playerChests[player] = chestGui
		return chestGui
	}


	override final fun enable() = Unit

	override final fun disable() {
		playerChests.forEach { onDestroy(it.key) }
		playerChests.clear()
	}

	fun deconstruct(player: Player) {
		onDestroy(player)
		playerChests.remove(player)
	}

}