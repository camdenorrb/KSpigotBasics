@file:JvmName("GsonExt")


package me.camdenorrb.kspigotbasics.gson.ext

import me.camdenorrb.kspigotbasics.ext.read
import me.camdenorrb.kspigotbasics.ext.write
import me.camdenorrb.kspigotbasics.struct.gson
import java.io.File


fun Any.toJson() = gson.toJson(this)!!

fun Any.writeJsonTo(file: File) = file.write { write(this@writeJsonTo.toJson()) }


inline fun <reified T : Any> String.readJson(): T = gson.fromJson(this, T::class.java)


inline fun <reified T : Any> File.readJson(): T? {
	if (exists().not()) return null
	return read { gson.fromJson(this, T::class.java) }
}

inline fun <reified T : Any> File.readJson(onDoesNotExist: () -> T): T {
	if (exists().not()) return onDoesNotExist()
	return read { gson.fromJson(this, T::class.java) }
}

inline fun <reified T : Any> File.readJson(defaultValue: T): T {
	if (exists().not()) return defaultValue.also { it.writeJsonTo(this) }
	return read { gson.fromJson(this, T::class.java) }
}