@file:JvmName("LoopUtils")

package me.camdenorrb.kspigotbasics.utils


inline fun innerWhile(block: () -> Boolean) {
	while (block()) {}
}