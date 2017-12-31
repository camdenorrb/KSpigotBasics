package me.camdenorrb.kspigotbasics.cache

import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveClass
import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveConstructor
import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveMethod
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import me.camdenorrb.kspigotbasics.utils.craftClass
import net.minecraft.server.v1_12_R1.Entity
import org.bukkit.EntityEffect.HURT
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action.RIGHT_CLICK_AIR
import org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.player.*
import org.bukkit.event.player.PlayerAnimationType.ARM_SWING


object DisguiseCache : ListeningModule() {

	val disguiseMap = mutableMapOf<Player, LivingEntity>()


	override fun onStop() {
		disguiseMap.clear()
	}


	@EventHandler(ignoreCancelled = true)
	fun onLeave(event: PlayerQuitEvent) {

		val player = event.player

		if (disguiseMap.remove(player) != null) server.onlinePlayers.forEach { it.showPlayer(player) }
		disguiseMap.keys.forEach { player.showPlayer(it) }
	}

	@EventHandler(ignoreCancelled = true)
	fun onMove(event: PlayerMoveEvent) {
		val disguise = disguiseMap[event.player] ?: return
		disguise.teleport(event.to)
	}

	@EventHandler(ignoreCancelled = true)
	fun onScroll(event: PlayerItemHeldEvent) {
		val disguise = disguiseMap[event.player] ?: return
		disguise.equipment.itemInMainHand = event.player.equipment.itemInMainHand
	}

	@EventHandler(ignoreCancelled = true)
	fun onDmgFromBlock(event: EntityDamageByBlockEvent) {

		val entity = event.entity
		val player = disguiseMap.entries.find { it.value == entity }?.key ?: return

		event.isCancelled = true

		player.damage(event.damage)
		entity.playEffect(HURT)
	}

	@EventHandler(ignoreCancelled = true)
	fun onDmgFromEntity(event: EntityDamageByEntityEvent) {

		val entity = event.entity
		val player = disguiseMap.entries.find { it.value == entity }?.key ?: return

		event.isCancelled = true

		player.damage(event.damage, event.damager)
		entity.playEffect(HURT)
	}

	@EventHandler(ignoreCancelled = true)
	fun onAnimation(event: PlayerAnimationEvent) {

		if (event.animationType != ARM_SWING) return

		val disguise = disguiseMap[event.player] ?: return

		val handle = retrieveMethod(craftClass("entity.CraftEntity"), "getHandle").invoke(disguise)
		val packetConstructor = retrieveConstructor(retrieveClass("PacketPlayOutAnimation"), Entity::class.java, Int::class.java)

		val packet = packetConstructor.newInstance(handle, 0)
		KBasicPlayerCache.basicPlayers.values.forEach { it.sendPacket(packet) }
	}


	@EventHandler(ignoreCancelled = true)
	fun onHandItemSwap(event: PlayerSwapHandItemsEvent) {
		val disguiseEquipment = disguiseMap[event.player]?.equipment ?: return
		disguiseEquipment.itemInMainHand = event.mainHandItem
		disguiseEquipment.itemInOffHand = event.offHandItem
	}

	@EventHandler(ignoreCancelled = true)
	fun onDraw(event: PlayerInteractEvent) {

		val action = event.action
		if (action != RIGHT_CLICK_AIR && action != RIGHT_CLICK_BLOCK) return

		val disguise = disguiseMap[event.player] ?: return

		val handle = retrieveMethod(craftClass("entity.CraftEntity"), "getHandle").invoke(disguise)
		val handleClass = handle.javaClass

		if (handleClass.interfaces.none { it.simpleName == "IRangedEntity" }) return

		retrieveMethod(handleClass, "p", Boolean::class.java).invoke(handle, true)
	}

	@EventHandler(ignoreCancelled = true)
	fun onShoot(event: EntityShootBowEvent) {

		val disguise = disguiseMap[event.entity] ?: return

		val handle = retrieveMethod(craftClass("entity.CraftEntity"), "getHandle").invoke(disguise)
		val handleClass = handle.javaClass

		if (handleClass.interfaces.none { it.simpleName == "IRangedEntity" }) return

		retrieveMethod(handleClass, "p", Boolean::class.java).invoke(handle, false)
	}

	/*@EventHandler(ignoreCancelled = true)
	fun onDeath(event: PlayerDeathEvent) {
		val disguise = disguiseMap[event.entity] ?: return
		disguise.damage(disguise.health)
	}

	@EventHandler(ignoreCancelled = true)
	fun onRespawn(event: PlayerRespawnEvent) {
		val disguise = disguiseMap[event.player] ?: return
		if (!disguise.isDead) disguise.damage(disguise.health)
		CraftSkeleton
		// TODO: Respawn Disguise and put into map
	}*/

}