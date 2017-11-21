package me.camdenorrb.kspigotbasics.scoreboard.ext

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team


operator fun Team.contains(entry: String) = hasEntry(entry)

operator fun Team.contains(entry: Player) = hasEntry(entry.name)


fun Team.add(entry: String) = addEntry(entry)

fun Team.add(entry: Player) = addEntry(entry.name)


fun Team.rem(entry: String) = removeEntry(entry)

fun Team.rem(entry: Player) = removeEntry(entry.name)