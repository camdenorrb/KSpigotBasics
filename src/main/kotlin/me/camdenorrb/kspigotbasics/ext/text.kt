package me.camdenorrb.kspigotbasics.ext

import org.bukkit.ChatColor.translateAlternateColorCodes


fun String.color(char: Char = '&') = translateAlternateColorCodes(char, this)