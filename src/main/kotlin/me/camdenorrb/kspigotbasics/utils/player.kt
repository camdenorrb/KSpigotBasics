@file:JvmName("PlayerUtils")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.OfflinePlayer
import java.util.*


fun findOfflinePlayer(name: String): OfflinePlayer?
	= server.getPlayer(name) ?: server.offlinePlayers.find { it.name == name }

fun findOfflinePlayer(uuid: UUID): OfflinePlayer?
	= server.getPlayer(uuid) ?: server.offlinePlayers.find { it.uniqueId == uuid }