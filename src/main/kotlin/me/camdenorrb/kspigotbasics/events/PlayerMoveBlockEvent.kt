package me.camdenorrb.kspigotbasics.events

import me.camdenorrb.minibus.event.CancellableMiniEvent
import org.bukkit.Location
import org.bukkit.entity.Player


data class PlayerMoveBlockEvent(val player: Player, val from: Location, val to: Location) : CancellableMiniEvent() {

	val toBlock by lazy { to.block!! }

	val fromBlock by lazy { from.block!! }

}