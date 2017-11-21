package me.camdenorrb.kspigotbasics.gui.utils

import me.camdenorrb.kspigotbasics.gui.types.ChestGui
import me.camdenorrb.kspigotbasics.gui.types.PlayerChestGui
import org.bukkit.entity.Player


fun chestGui(title: String = "", size: Int = 27, block: ChestGui.() -> Unit) = object : ChestGui(title, size) {

	override fun construct() = block(this)

}.apply { enable() }


fun playerChestGui(title: String = "", size: Int = 27, block: ChestGui.(Player) -> Unit) = object : PlayerChestGui(title, size) {

	override fun onConstruct(player: Player, chestGui: ChestGui) = block(chestGui, player)

}.apply { enable() }