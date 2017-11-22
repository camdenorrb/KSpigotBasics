@file:JvmName("SkullUtils")


package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.ext.skullMeta
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import java.util.*


fun OfflinePlayer.skull() = ItemStack(Material.SKULL_ITEM, 1, 3).skullMeta {
	owner = this@skull.name
}

fun skull(playerName: String) = ItemStack(Material.SKULL_ITEM, 1, 3).skullMeta {
	owner = playerName
}

fun skull(uuid: UUID) = ItemStack(Material.SKULL_ITEM, 1, 3).skullMeta {
	owner = findOfflinePlayer(uuid)?.name
}
