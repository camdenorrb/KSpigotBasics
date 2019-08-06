package me.camdenorrb.kspigotbasics.progression

import me.camdenorrb.kspigotbasics.progression.iterator.LocProgressionIterator
import org.bukkit.Location


data class LocProgression(val start: Location, val end: Location, var step: Int = 1) : Iterable<Location> {

	override fun iterator() = LocProgressionIterator(start, end, step)

}


operator fun Location.rangeTo(end: Location) = LocProgression(this, end)
