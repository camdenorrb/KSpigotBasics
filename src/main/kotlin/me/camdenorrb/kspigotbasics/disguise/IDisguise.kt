package me.camdenorrb.kspigotbasics.disguise

import org.bukkit.Location
import org.bukkit.util.Vector


// NOTE: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/LivingEntity.html#setRemoveWhenFarAway-boolean-
interface IDisguise {

	/*var entity: LivingEntity
		private set
	*/

	// Don't forget to set AI to false!

	fun bowShoot() = Unit

	fun bowLoading() = Unit


	fun combust(ticks: Int) = Unit


	fun move(loc: Location) = Unit

	fun spawn(loc: Location) = Unit

	fun respawn(loc: Location) = Unit

	fun teleport(loc: Location) = Unit

	fun setVelocity(vector: Vector) = Unit

}