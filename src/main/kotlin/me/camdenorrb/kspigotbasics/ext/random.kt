@file:JvmName("RandomExt")

package me.camdenorrb.kspigotbasics.ext

import me.camdenorrb.kspigotbasics.struct.random as rand


fun IntArray.random() =
	if (size == 1) first()
	else this[rand.nextInt(size)]

fun LongArray.random() =
	if (size == 1) first()
	else this[rand.nextInt(size)]

fun ByteArray.random() =
	if (size == 1) first()
	else this[rand.nextInt(size)]


fun <T> List<T>.random() =
	if (size == 1) first()
	else this[rand.nextInt(size)]

fun <T> Array<T>.random() =
	if (size == 1) first()
	else this[rand.nextInt(size)]

fun <T> Set<T>.random() =
	if (size == 1) first()
	else elementAt(rand.nextInt(size))


fun <K, V> Map<K, V>.random(): Map.Entry<K, V> {
	val entries = this.entries
	return if (size == 1) entries.first() else entries.random()
}