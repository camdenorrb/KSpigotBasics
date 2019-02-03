@file:JvmName("BlockFaceExt")

package me.camdenorrb.kspigotbasics.ext

import org.bukkit.block.BlockFace
import org.bukkit.util.Vector

/**
 * Converts a [org.bukkit.block.BlockFace] to a Direction [org.bukkit.util.Vector]
 */
fun BlockFace.toDir() = Vector(modX, modY, modZ)