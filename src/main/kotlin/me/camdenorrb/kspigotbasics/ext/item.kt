package me.camdenorrb.kspigotbasics.ext

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*




// Meta Extensions

inline fun ItemStack.meta(block: ItemMeta.() -> Unit): ItemStack {
	itemMeta = itemMeta.also(block)
	return this
}


inline fun ItemStack.mapMeta(block: MapMeta.() -> Unit): ItemStack {
	return meta { block(this as MapMeta) }
}

inline fun ItemStack.skullMeta(block: SkullMeta.() -> Unit): ItemStack {
	return meta { block(this as SkullMeta) }
}

inline fun ItemStack.potionMeta(block: PotionMeta.() -> Unit): ItemStack {
	return meta { block(this as PotionMeta) }
}

inline fun ItemStack.fireWorkMeta(block: FireworkMeta.() -> Unit): ItemStack {
	return meta { block(this as FireworkMeta) }
}

inline fun ItemStack.spawnEggMeta(block: SpawnEggMeta.() -> Unit): ItemStack {
	return meta { block(this as SpawnEggMeta) }
}

inline fun ItemStack.leatherArmorMeta(block: LeatherArmorMeta.() -> Unit): ItemStack {
	return meta { block(this as LeatherArmorMeta) }
}

inline fun ItemStack.knowledgeBookMeta(block: KnowledgeBookMeta.() -> Unit): ItemStack {
	return meta { block(this as KnowledgeBookMeta) }
}

inline fun ItemStack.fireWorkEffectMeta(block: FireworkEffectMeta.() -> Unit): ItemStack {
	return meta { block(this as FireworkEffectMeta) }
}

inline fun ItemStack.enchantStorageMeta(block: EnchantmentStorageMeta.() -> Unit): ItemStack {
	return meta { block(this as EnchantmentStorageMeta) }
}








