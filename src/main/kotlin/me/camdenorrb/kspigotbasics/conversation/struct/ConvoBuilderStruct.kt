package me.camdenorrb.kspigotbasics.conversation.struct

import me.camdenorrb.kspigotbasics.conversation.impl.Conversation
import me.camdenorrb.kspigotbasics.utils.converseWith
import org.bukkit.entity.Player

abstract class ConvoBuilderStruct<T : Any> {

	protected abstract val conversation: suspend Conversation.() -> T


	fun build(player: Player, block: (T) -> Unit) {
		converseWith(player) {
			block(conversation(this))
		}
	}

}