@file:JvmName("LocUtils")

package me.camdenorrb.kspigotbasics.location.utils

import org.bukkit.Location
import kotlin.math.max
import kotlin.math.min


fun createMin(loc1: Location, loc2: Location): Location {
	return Location(loc1.world, min(loc1.x, loc2.x), min(loc1.y, loc2.y), min(loc1.z, loc2.z))
}

fun createMax(loc1: Location, loc2: Location): Location {
	return Location(loc1.world, max(loc1.x, loc2.x), max(loc1.y, loc2.y), max(loc1.z, loc2.z))
}