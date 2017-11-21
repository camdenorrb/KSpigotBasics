package me.camdenorrb.kspigotbasics.player

import org.bukkit.entity.Player


class KBasicPlayer(player: Player) : Player by player {

	val handleObject by lazy {
		player::class.java.getMethod("getHandle").invoke(player)!!
	}

	val connectionObject by lazy {
		handleObject::class.java.getField("playerConnection").get(handleObject)!!
	}

	val sendPacketMethod by lazy {
		connectionObject::class.java.methods.find { it.name == "sendPacket" }!!
	}


	fun sendPacket(packet: Any) { sendPacketMethod.invoke(connectionObject, packet) }

}