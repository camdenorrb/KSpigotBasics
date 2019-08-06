package me.camdenorrb.kspigotbasics.cache

/*
import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveConstructor
import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveField
import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveMethod
import me.camdenorrb.kspigotbasics.struct.dataWatcherSetMethod
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import me.camdenorrb.kspigotbasics.utils.nmsClass
import org.bukkit.EntityEffect.HURT
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action.RIGHT_CLICK_AIR
import org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK
import org.bukkit.event.entity.*
import org.bukkit.event.entity.EntityDamageEvent.DamageCause.*
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType.SlotType.ARMOR
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
	fun onEquip(event: InventoryClickEvent) {

		if (event.slotType != ARMOR) return

		val player = event.clickedInventory.holder as? Player ?: return

		val playerEquipment = player.equipment
		val disguiseEquipment = disguiseMap[player]?.equipment ?: return

		when(event.slot) {
			103 -> disguiseEquipment.helmet = playerEquipment.helmet
			102 -> disguiseEquipment.chestplate = playerEquipment.chestplate
			101 -> disguiseEquipment.leggings = playerEquipment.leggings
			100 -> disguiseEquipment.boots = playerEquipment.boots
		}

	}

	@EventHandler(ignoreCancelled = true)
	fun onMove(event: PlayerMoveEvent) {
		val disguise = disguiseMap[event.player] ?: return
		disguise.teleport(event.to)
	}

	@EventHandler(ignoreCancelled = true)
	fun onScroll(event: PlayerItemHeldEvent) {
		val disguise = disguiseMap[event.player] ?: return
		disguise.equipment.itemInMainHand = event.player.inventory.getItem(event.newSlot)
	}

	@EventHandler(ignoreCancelled = true)
	fun onCombust(event: EntityCombustEvent) {

		val entity = event.entity
		if (entity is Player) { disguiseMap[entity]?.fireTicks = entity.fireTicks; return }

		if (event is EntityCombustByEntityEvent || event is EntityCombustByBlockEvent) return

		val player = disguiseMap.entries.find { it.value == entity }?.key ?: return
		if (player.fireTicks <= 0) event.isCancelled = true
	}

	@EventHandler(ignoreCancelled = true)
	fun onDmg(event: EntityDamageEvent) {

		val cause = event.cause
		if (cause == ENTITY_ATTACK || cause == ENTITY_EXPLOSION || cause == ENTITY_SWEEP_ATTACK) return

		val entity = event.entity.takeIf { it !is Player } ?: return
		val player = disguiseMap.entries.find { it.value == entity }?.key ?: return

		val damageCache = event.damage
		event.damage = 0.0

		if (cause == DROWNING) return

		entity.playEffect(HURT)
		if (cause == FIRE_TICK) return

		player.damage(damageCache)
	}

	@EventHandler(ignoreCancelled = true)
	fun onDmgFromEntity(event: EntityDamageByEntityEvent) {

		val entity = event.entity
		val player = disguiseMap.entries.find { it.value == entity }?.key ?: return

		player.damage(event.damage, event.damager)

		event.damage = 0.0
	}

	@EventHandler(ignoreCancelled = true)
	fun onAnimation(event: PlayerAnimationEvent) {

		if (event.animationType != ARM_SWING) return

		val disguise = disguiseMap[event.player] ?: return

		val handle = retrieveMethod(disguise.javaClass, "getHandle").invoke(disguise)
		val packetConstructor = retrieveConstructor(nmsClass("PacketPlayOutAnimation"), nmsClass("Entity"), Int::class.java)

		val packet = packetConstructor.newInstance(handle, 0)
		KBasicPlayerCache.basicPlayers.values.forEach { it.sendPacket(packet) }
	}


	/* Not supported in 1.8

	@EventHandler(ignoreCancelled = true)
	fun onHandItemSwap(event: PlayerSwapHandItemsEvent) {
		val disguiseEquipment = disguiseMap[event.player]?.equipment ?: return
		disguiseEquipment.itemInMainHand = event.mainHandItem
		disguiseEquipment.itemInOffHand = event.offHandItem
	}*/

	/*
	[21:07] <md_5> http://wiki.vg/Entity_metadata#Living
	[21:07] <md_5> is hand active
	 */
	@EventHandler(ignoreCancelled = true)
	fun onDraw(event: PlayerInteractEvent) {

		val action = event.action
		if (action != RIGHT_CLICK_AIR && action != RIGHT_CLICK_BLOCK) return

		val disguise = disguiseMap[event.player] ?: return

		val handle = retrieveMethod(disguise.javaClass, "getHandle").invoke(disguise)
		val handleClass = handle.javaClass

		if (!nmsClass("IRangedEntity").isAssignableFrom(handleClass)) return

		val dataWatcher = retrieveField(nmsClass("Entity"), "datawatcher").apply { isAccessible = true }.get(handle)

		when(dataWatcherSetMethod.name) {

			"set" -> {
				dataWatcherSetMethod.invoke(dataWatcher, retrieveField(handleClass.superclass, "a").apply { isAccessible = true }.get(handle), true)
			}

			"watch" -> {
				dataWatcherSetMethod.invoke(dataWatcher, 13, (1).toByte())
			}

		}

	}

	@EventHandler(ignoreCancelled = true)
	fun onShoot(event: EntityShootBowEvent) {

		val disguise = disguiseMap[event.entity] ?: return

		val handle = retrieveMethod(disguise.javaClass, "getHandle").invoke(disguise)
		val handleClass = handle.javaClass

		if (!nmsClass("IRangedEntity").isAssignableFrom(handleClass)) return

		val dataWatcher = retrieveField(nmsClass("Entity"), "datawatcher").apply { isAccessible = true }.get(handle)

		when (dataWatcherSetMethod.name) {

			"set" -> {
				dataWatcherSetMethod.invoke(dataWatcher, retrieveField(handleClass.superclass, "a").apply { isAccessible = true }.get(handle), false)
			}

			"watch" -> {
				dataWatcherSetMethod.invoke(dataWatcher, 13, (0).toByte())
			}

		}

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
		// TODO: Respawn IDisguise and put into map
	}*/

}*/