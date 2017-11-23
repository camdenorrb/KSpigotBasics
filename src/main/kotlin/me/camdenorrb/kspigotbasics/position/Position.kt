package me.camdenorrb.kspigotbasics.position

import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.Location
import java.util.*


data class Position @JvmOverloads constructor(var worldUID: UUID, var x: Double, var y: Double, var z: Double, var yaw: Float = 0f, var pitch: Float = 0f) {

	constructor(loc: Location) : this(loc.world.uid, loc.x, loc.y, loc.z, loc.yaw, loc.pitch)

	fun toLoc() = Location(server.getWorld(worldUID), x, y, z, yaw, pitch)

}