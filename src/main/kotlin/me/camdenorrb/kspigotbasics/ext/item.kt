@file:JvmName("ItemExt")

package me.camdenorrb.kspigotbasics.ext

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import java.util.function.Consumer


// Meta Extensions

@JvmSynthetic
inline fun ItemStack.meta(block: ItemMeta.() -> Unit): ItemStack {
	itemMeta = itemMeta.also(block)
	return this
}

@JvmSynthetic
inline fun ItemStack.mapMeta(block: MapMeta.() -> Unit): ItemStack {
	return meta { block(this as MapMeta) }
}

inline fun ItemStack.skullMeta(block: SkullMeta.() -> Unit): ItemStack {
	return meta { block(this as SkullMeta) }
}

@JvmSynthetic
inline fun ItemStack.potionMeta(block: PotionMeta.() -> Unit): ItemStack {
	return meta { block(this as PotionMeta) }
}

@JvmSynthetic
inline fun ItemStack.fireWorkMeta(block: FireworkMeta.() -> Unit): ItemStack {
	return meta { block(this as FireworkMeta) }
}

@JvmSynthetic
inline fun ItemStack.spawnEggMeta(block: SpawnEggMeta.() -> Unit): ItemStack {
	return meta { block(this as SpawnEggMeta) }
}

@JvmSynthetic
inline fun ItemStack.leatherArmorMeta(block: LeatherArmorMeta.() -> Unit): ItemStack {
	return meta { block(this as LeatherArmorMeta) }
}

@JvmSynthetic
inline fun ItemStack.knowledgeBookMeta(block: KnowledgeBookMeta.() -> Unit): ItemStack {
	return meta { block(this as KnowledgeBookMeta) }
}

@JvmSynthetic
inline fun ItemStack.fireWorkEffectMeta(block: FireworkEffectMeta.() -> Unit): ItemStack {
	return meta { block(this as FireworkEffectMeta) }
}

@JvmSynthetic
inline fun ItemStack.enchantStorageMeta(block: EnchantmentStorageMeta.() -> Unit): ItemStack {
	return meta { block(this as EnchantmentStorageMeta) }
}



// For the Java users ;)

fun ItemStack.meta(consumer: Consumer<ItemMeta>) = meta {
	consumer.accept(this)
}


fun ItemStack.mapMeta(consumer: Consumer<MapMeta>) = mapMeta {
	consumer.accept(this)
}

fun ItemStack.skullMeta(consumer: Consumer<SkullMeta>) = skullMeta {
	consumer.accept(this)
}

fun ItemStack.potionMeta(consumer: Consumer<PotionMeta>) = potionMeta {
	consumer.accept(this)
}

fun ItemStack.fireWorkMeta(consumer: Consumer<FireworkMeta>) = fireWorkMeta {
	consumer.accept(this)
}

fun ItemStack.spawnEggMeta(consumer: Consumer<SpawnEggMeta>) = spawnEggMeta {
	consumer.accept(this)
}

fun ItemStack.leatherArmorMeta(consumer: Consumer<LeatherArmorMeta>) = leatherArmorMeta {
	consumer.accept(this)
}

fun ItemStack.knowledgeBookMeta(consumer: Consumer<KnowledgeBookMeta>) = knowledgeBookMeta {
	consumer.accept(this)
}

fun ItemStack.fireWorkEffectMeta(consumer: Consumer<FireworkEffectMeta>) = fireWorkEffectMeta {
	consumer.accept(this)
}


fun ItemStack.enchantStorageMeta(consumer: Consumer<EnchantmentStorageMeta>) = enchantStorageMeta {
	consumer.accept(this)
}