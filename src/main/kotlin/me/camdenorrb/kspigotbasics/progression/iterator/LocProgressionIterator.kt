package me.camdenorrb.kspigotbasics.progression.iterator

import me.camdenorrb.kspigotbasics.location.utils.createMax
import me.camdenorrb.kspigotbasics.location.utils.createMin
import org.bukkit.Location


class LocProgressionIterator(val loc1: Location, val loc2: Location, var step: Int) : Iterator<Location> {

	private val world = loc1.world


	private val start = createMin(loc1, loc2)

	private var x: Double = start.x
	private var y: Double = start.y
	private var z: Double = start.z


	private val end = createMax(loc1, loc2)

	private val endX: Double = end.x
	private val endY: Double = end.y
	private val endZ: Double = end.z


	override fun hasNext() = x <= endX


	override fun next(): Location {

		val next = Location(world, x, y, z)

		if (++z <= endZ) return next
		z = start.z

		if (++y <= endY) return next
		y = start.y

		x += step

		return next
	}


	infix fun step(step: Int): LocProgressionIterator {
		this.step = step
		return this
	}

}