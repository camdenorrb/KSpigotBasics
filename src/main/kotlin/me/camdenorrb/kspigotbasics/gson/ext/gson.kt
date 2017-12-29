@file:JvmName("GsonExt")


package me.camdenorrb.kspigotbasics.gson.ext

import me.camdenorrb.kspigotbasics.ext.read
import me.camdenorrb.kspigotbasics.ext.write
import me.camdenorrb.kspigotbasics.struct.gson
import java.io.File
import java.lang.reflect.Type


fun Any.toJson() = gson.toJson(this)!!

fun Any.writeJsonTo(file: File) = file.write { write(this@writeJsonTo.toJson()) }


inline fun <reified T : Any> String.readJson(): T = gson.fromJson(this, T::class.java)


inline fun <reified T : Any> File.readJson(): T? = readJson(T::class.java)

inline fun <reified T : Any> File.readJson(type: Type): T? {
	if (exists().not()) return null
	return read { gson.fromJson(this, type) }
}


inline fun <reified T : Any> File.readJson(onDoesNotExist: File.() -> T) = readJson(T::class.java, onDoesNotExist)

inline fun <reified T : Any> File.readJson(type: Type, onDoesNotExist: File.() -> T): T {

	if (exists().not()) return onDoesNotExist()

	val json = readText().takeIf { it.isNotBlank() } ?: return onDoesNotExist()
	return gson.fromJson(json, type)
}


inline fun <reified T : Any> File.readJson(defaultValue: T) = readJson(T::class.java, defaultValue)

inline fun <reified T : Any> File.readJson(type: Type, defaultValue: T): T {

	if (exists().not()) return defaultValue.also { it.writeJsonTo(this) }

	val json = readText().takeIf { it.isNotBlank() } ?: return defaultValue.also { it.writeJsonTo(this) }
	return gson.fromJson(json, type)
}