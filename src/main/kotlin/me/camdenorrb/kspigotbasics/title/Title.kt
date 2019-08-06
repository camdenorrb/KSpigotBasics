package me.camdenorrb.kspigotbasics.title

import me.camdenorrb.kspigotbasics.ext.kBasic
import me.camdenorrb.kspigotbasics.struct.chatSerializerAMethod
import me.camdenorrb.kspigotbasics.struct.enumTitleActionClass
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.utils.nmsClass
import org.bukkit.entity.Player


private val titleType by lazy {
	enumTitleActionClass.enumConstants[0]
}

private val subtitleType by lazy {
	enumTitleActionClass.enumConstants[1]
}

private val outTitleConstruct by lazy {
	nmsClass("PacketPlayOutTitle").declaredConstructors.first()
}


data class Title @JvmOverloads constructor(var title: String, var subtitle: String, var fadeIn: Int = 1, var stay: Int = 1, var fadeOut: Int = 1) {

	
	fun send(player: Player) {

		val chatTitle = chatSerializerAMethod.invoke(null, """{"text": "$title"}""")
		val chatSubtitle = chatSerializerAMethod.invoke(null, """{"text": "$subtitle"}""")

		val titlePacket = outTitleConstruct.newInstance(titleType, chatTitle, fadeIn * 20, stay * 20, fadeOut * 20)
		val subTitlePacket = outTitleConstruct.newInstance(subtitleType, chatSubtitle, fadeIn * 20, stay * 20, fadeOut * 20)

		val basicPlayer = player.kBasic()
		basicPlayer.sendPacket(titlePacket)
		basicPlayer.sendPacket(subTitlePacket)
	}

	
	fun sendAll() {

		val chatTitle = chatSerializerAMethod.invoke(null, """{"text": "$title"}""")
		val chatSubtitle = chatSerializerAMethod.invoke(null, """{"text": "$subtitle"}""")

		val titlePacket = outTitleConstruct.newInstance(titleType, chatTitle, fadeIn * 20, stay * 20, fadeOut * 20)
		val subTitlePacket = outTitleConstruct.newInstance(subtitleType, chatSubtitle, fadeIn * 20, stay * 20, fadeOut * 20)

		server.onlinePlayers.forEach {
			val basicPlayer = it.kBasic()
			basicPlayer.sendPacket(titlePacket)
			basicPlayer.sendPacket(subTitlePacket)
		}

	}

}