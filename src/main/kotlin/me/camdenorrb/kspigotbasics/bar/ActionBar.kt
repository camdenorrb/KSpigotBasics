package me.camdenorrb.kspigotbasics.bar

import me.camdenorrb.kspigotbasics.ext.kBasic
import me.camdenorrb.kspigotbasics.struct.chatSerializerAMethod
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.utils.nmsClass
import org.bukkit.entity.Player


private val outChatConstruct by lazy {
	nmsClass("PacketPlayOutChat").declaredConstructors.first()
}

private val gameInfoType by lazy {
	nmsClass("ChatMessageType").enumConstants[2]
}


data class ActionBar(val text: String) {



	fun send(player: Player) {

		val serializer = chatSerializerAMethod.invoke(null, """{"text": "$text"}""")
		val version = server.bukkitVersion

		val packetObject =
			if (version.startsWith("1.12")) outChatConstruct.newInstance(serializer, gameInfoType)
			else outChatConstruct.newInstance(serializer, 2.toByte())

		player.kBasic().sendPacket(packetObject)
	}


	fun sendAll() {


		val serializer = chatSerializerAMethod.invoke(null, """{"text": "$text"}""")
		val version = server.bukkitVersion

		val packetObject =
			if (version.startsWith("1.12")) outChatConstruct.newInstance(serializer, gameInfoType)
			else outChatConstruct.newInstance(serializer, 2.toByte())

		server.onlinePlayers.forEach { it.kBasic().sendPacket(packetObject) }
	}

}