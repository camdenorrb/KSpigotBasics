@file:JvmName("PlayerExt")

package me.camdenorrb.kspigotbasics.ext

import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import org.bukkit.entity.Player


fun Player.kBasic() = KBasicPlayerCache.basicPlayers[this]!!