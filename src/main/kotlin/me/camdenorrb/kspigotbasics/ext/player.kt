package me.camdenorrb.kspigotbasics.ext

import me.camdenorrb.kspigotbasics.cache.KBasicPlayerCache
import org.bukkit.Material.SKULL_ITEM
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


fun Player.kBasic() = KBasicPlayerCache.basicPlayers[this]!!


fun Player.skull() = ItemStack(SKULL_ITEM, 1, 3).skullMeta {
	owner = this@skull.name
}