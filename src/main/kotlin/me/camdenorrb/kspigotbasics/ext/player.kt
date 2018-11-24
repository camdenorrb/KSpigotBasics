@file:JvmName("PlayerExt")

package me.camdenorrb.kspigotbasics.ext

//import me.camdenorrb.kspigotbasics.cache.DisguiseCache
import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import org.bukkit.entity.Player


fun Player.kBasic() = KBasicPlayerCache.basicPlayers[this]!!

/*
fun Player.disguise(entityType: EntityType) {

	check(entityType.isAlive) { "Tried to disguise as a non-living entity!" }

	val disguise = world.spawnEntity(location, entityType) as LivingEntity
	disguise.setAI(false)

	DisguiseCache.disguiseMap[player] = disguise
	server.onlinePlayers.forEach { it.hidePlayer(this) }
}*/