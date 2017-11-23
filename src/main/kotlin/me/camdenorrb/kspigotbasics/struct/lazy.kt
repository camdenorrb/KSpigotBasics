@file:JvmName("LazyStruct")


package me.camdenorrb.kspigotbasics.struct

import me.camdenorrb.kspigotbasics.utils.nmsClass
import me.camdenorrb.minibus.MiniBus


val miniBus by lazy { MiniBus() }



// NMS Reflection

internal val enumTitleActionClass by lazy {
	nmsClass("PacketPlayOutTitle\$EnumTitleAction")
}

internal val chatSerializerAMethod by lazy {
	nmsClass("IChatBaseComponent\$ChatSerializer").getMethod("a", String::class.java)!!
}
