@file:JvmName("PlayerUtils")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.OfflinePlayer
import java.util.*


fun findOfflinePlayer(uuid: UUID): OfflinePlayer?
	= server.getPlayer(uuid) ?: server.offlinePlayers.find { it.uniqueId == uuid }