@file:JvmName("LazyStruct")


package me.camdenorrb.kspigotbasics.struct

import me.camdenorrb.kspigotbasics.KSpigotBasics
import me.camdenorrb.kspigotbasics.cache.ReflectCache.retrieveMethod
import me.camdenorrb.kspigotbasics.utils.nmsClass
import me.camdenorrb.minibus.MiniBus
import org.bukkit.World


val miniBus by lazy { MiniBus() }

val spigotBasics by lazy { KSpigotBasics.instance }


val mainWorld: World by lazy { server.worlds.first() }


// NMS Reflection
// TODO: Make a Reflection object which automagically caches results

internal val dataWatcherSetMethod by lazy {

	val dataWatcherClazz = nmsClass("DataWatcher")

	try { retrieveMethod(dataWatcherClazz, "set", nmsClass("DataWatcherObject"), Any::class.java) }
	catch (ex: NoSuchMethodException) { retrieveMethod(dataWatcherClazz, "watch", Int::class.java, Byte::class.java) }
}

internal val enumTitleActionClass by lazy {
	try { nmsClass("PacketPlayOutTitle\$EnumTitleAction") }
	catch (ex: ClassNotFoundException) { nmsClass("EnumTitleAction") }
}

internal val chatSerializerAMethod by lazy {

	val clazz = try {
		nmsClass("IChatBaseComponent\$ChatSerializer")
	} catch (ex: ClassNotFoundException) {
		nmsClass("ChatSerializer")
	}

	clazz.getMethod("a", String::class.java)!!
}

