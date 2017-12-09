@file:JvmName("LazyStruct")


package me.camdenorrb.kspigotbasics.struct

import me.camdenorrb.kspigotbasics.utils.nmsClass
import me.camdenorrb.minibus.MiniBus


val miniBus by lazy { MiniBus() }

val mainWorld by lazy { server.worlds.first() }


// NMS Reflection

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

