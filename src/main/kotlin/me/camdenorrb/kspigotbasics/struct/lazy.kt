package me.camdenorrb.kspigotbasics.struct

import me.camdenorrb.kspigotbasics.utils.nmsClass


val pluginManager by lazy { server.pluginManager!! }



// NMS Reflection

val enumTitleActionClass by lazy {
	nmsClass("PacketPlayOutTitle\$EnumTitleAction")
}

val chatSerializerAMethod by lazy {
	nmsClass("IChatBaseComponent\$ChatSerializer").getMethod("a", String::class.java)!!
}
