package me.camdenorrb.kspigotbasics.ext

import org.bukkit.ChatColor.translateAlternateColorCodes


@JvmOverloads
fun String.color(char: Char = '&') = translateAlternateColorCodes(char, this)