package me.camdenorrb.kspigotbasics.disguise

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity


class LivingDisguise(val entityType: EntityType) : IDisguise {

	lateinit var entity: LivingEntity
		private set


	override fun spawn(loc: Location) {
		entity = loc.world.spawnEntity(loc, entityType) as LivingEntity
		entity.setAI(false)
	}

	override fun teleport(loc: Location) {

	}

	override fun move(loc: Location) {

	}

	override fun bowLoading() {

	}

	override fun bowShoot() {

	}

	override fun respawn(loc: Location) {
		entity.remove()
		spawn(loc)
	}


}