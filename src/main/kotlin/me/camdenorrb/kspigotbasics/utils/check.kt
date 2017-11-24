@file:JvmName("CheckUtils")

package me.camdenorrb.kspigotbasics.utils


fun noneNull(vararg values: Any?) = values.none { it == null }