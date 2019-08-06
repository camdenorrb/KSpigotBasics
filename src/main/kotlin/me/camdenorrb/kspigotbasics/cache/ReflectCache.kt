@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.kspigotbasics.cache

import me.camdenorrb.kspigotbasics.types.modules.ModuleImpl
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method


object ReflectCache : ModuleImpl() {

	val cachedClasses = mutableMapOf<String, Class<*>>()

	val cachedFields = mutableMapOf<Class<*>, MutableSet<Field>>()

	val cachedMethods = mutableMapOf<Class<*>, MutableSet<Method>>()

	val cachedConstructors = mutableMapOf<Class<*>, MutableSet<Constructor<*>>>()


	override fun onDisable() {
		cachedFields.clear()
		cachedMethods.clear()
		cachedConstructors.clear()
	}


	fun retrieveClass(path: String) : Class<*> {
		val foundClass = cachedClasses[path]
		return foundClass ?: Class.forName(path).also { cachedClasses[path] = it }
	}

	fun <T> retrieveField(clazz: Class<T>, name: String): Field {
		val foundField = cachedFields[clazz]?.find { it.name == name }
		return foundField ?: clazz.getDeclaredField(name).also { cachedFields.getOrPut(clazz, { mutableSetOf() }).add(it) }
	}

	fun <T> retrieveMethod(clazz: Class<T>, name: String, vararg paramTypes: Class<*>): Method {
		val foundMethod = cachedMethods[clazz]?.find { it.name == name && it.parameters!!.contentEquals(paramTypes) }
		return foundMethod ?: clazz.getDeclaredMethod(name, *paramTypes)
	}

	fun <T> retrieveConstructor(clazz: Class<T>, vararg paramTypes: Class<*>): Constructor<T> {
		val foundConstructor = cachedConstructors[clazz]?.find { it.parameters!!.contentEquals(paramTypes) }
		return foundConstructor as? Constructor<T> ?: clazz.getDeclaredConstructor(*paramTypes)
	}

}