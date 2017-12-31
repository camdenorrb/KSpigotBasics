@file:JvmName("ReflectUtils")

package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.cache.ReflectCache
import me.camdenorrb.kspigotbasics.struct.server


private val version by lazy { server.javaClass.`package`.name.substring(23) }


fun nmsClass(name: String) = ReflectCache.retrieveClass("net.minecraft.server.$version.$name")

fun craftClass(name: String) = ReflectCache.retrieveClass("org.bukkit.craftbukkit.$version.$name")