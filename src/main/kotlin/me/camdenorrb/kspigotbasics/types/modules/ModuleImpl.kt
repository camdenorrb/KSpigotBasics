package me.camdenorrb.kspigotbasics.types.modules


abstract class ModuleImpl : Module {

	@Transient var isEnabled = false
		private set


	protected open fun onEnable() = Unit

	protected open fun onDisable() = Unit


	override final fun enable() {

		if (isEnabled) return

		onEnable()
		isEnabled = true
	}

	override final fun disable() {

		if (!isEnabled) return

		onDisable()
		isEnabled = false
	}

}