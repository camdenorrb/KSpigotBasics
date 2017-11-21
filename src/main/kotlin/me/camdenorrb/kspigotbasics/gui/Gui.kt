package me.camdenorrb.kspigotbasics.gui

import me.camdenorrb.kspigotbasics.types.Constructable
import me.camdenorrb.kspigotbasics.types.Openable
import org.bukkit.entity.Player


interface Gui : Constructable, Openable<Player> {

	override fun open(t: Player) = Unit

}