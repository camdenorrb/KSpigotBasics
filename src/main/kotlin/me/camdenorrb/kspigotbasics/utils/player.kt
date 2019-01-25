@file:JvmName("PlayerUtils")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.conversation.impl.Conversation
import me.camdenorrb.kspigotbasics.struct.server
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.*


fun findOfflinePlayer(name: String): OfflinePlayer?
	= server.getPlayer(name) ?: server.offlinePlayers.find { it.name == name }

fun findOfflinePlayer(uuid: UUID): OfflinePlayer?
	= server.getPlayer(uuid) ?: server.offlinePlayers.find { it.uniqueId == uuid }


fun converseWith(player: Player, block: suspend Conversation.() -> Unit) {
	Conversation(player, block = block).enable()
}