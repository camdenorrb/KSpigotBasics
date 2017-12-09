package me.camdenorrb.kspigotbasics.ext

import me.camdenorrb.kspigotbasics.struct.random as rand


fun IntArray.random() = this[rand.nextInt(size)]

fun LongArray.random() = this[rand.nextInt(size)]

fun ByteArray.random() = this[rand.nextInt(size)]


fun <T> List<T>.random() = this[rand.nextInt(size)]

fun <T> Array<T>.random() = this[rand.nextInt(size)]


fun <T> Collection<T>.random() = take(rand.nextInt(size)).last()