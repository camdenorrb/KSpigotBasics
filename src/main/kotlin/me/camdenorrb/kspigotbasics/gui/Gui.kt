package me.camdenorrb.kspigotbasics.gui

import me.camdenorrb.kspigotbasics.types.Openable
import org.bukkit.entity.Player


interface Gui : Openable<Player> {

	override fun open(target: Player) = Unit

}