package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.struct.server


private val version by lazy { server.javaClass.`package`.name.substring(23) }


fun nmsClass(name: String) = Class.forName("net.minecraft.server.$version.$name")!!

fun craftClass(name: String) = Class.forName("org.bukkit.craftbukkit.$version.$name")!!