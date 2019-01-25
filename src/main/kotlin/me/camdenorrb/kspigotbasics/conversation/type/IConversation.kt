package me.camdenorrb.kspigotbasics.conversation.type

import me.camdenorrb.kspigotbasics.types.Named
import me.camdenorrb.kspigotbasics.types.modules.Module
import org.bukkit.command.CommandSender

abstract class IConversation<T : CommandSender> : Named, Module {

	abstract val target: T

	var isConversing = false
		private set


	override val name by lazy {
		"IConversation(${target.name})"
	}


	protected open fun onStop() = Unit

	protected open fun onStart() = Unit


	override fun enable() {

		if (isConversing) return

		onStart()
		isConversing = true
	}

	override fun disable() {

		if (!isConversing) return

		onStop()
		isConversing = false
	}

}