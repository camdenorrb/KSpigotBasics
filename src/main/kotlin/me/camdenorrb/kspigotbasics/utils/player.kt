@file:JvmName("PlayerUtils")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.ext.skullMeta
import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import java.util.*


fun findOfflinePlayer(uuid: UUID): OfflinePlayer?
	= server.getPlayer(uuid) ?: server.offlinePlayers.find { it.uniqueId == uuid }


fun skull(playerName: String) = ItemStack(Material.SKULL_ITEM, 1, 3).skullMeta {
	owner = playerName
}

fun skull(uuid: UUID) = ItemStack(Material.SKULL_ITEM, 1, 3).skullMeta {
	owner = findOfflinePlayer(uuid)?.name
}
