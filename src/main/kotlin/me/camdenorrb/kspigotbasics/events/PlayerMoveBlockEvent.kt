package me.camdenorrb.kspigotbasics.events

import org.bukkit.Location
import org.bukkit.entity.Player


data class PlayerMoveBlockEvent(val player: Player, val from: Location, val to: Location) {

	var isCancelled: Boolean = false

	val toBlock by lazy { to.block }

	val fromBlock by lazy { from.block }

}