@file:JvmName("DefaultUtils")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.struct.mainWorld
import org.bukkit.Location


fun defaultLoc() = Location(mainWorld, 0.0, 0.0, 0.0).apply {
	y = mainWorld.getHighestBlockYAt(this).toDouble()
}