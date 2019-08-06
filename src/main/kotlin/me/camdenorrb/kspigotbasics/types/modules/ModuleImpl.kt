package me.camdenorrb.kspigotbasics.types.modules


abstract class ModuleImpl : Module {

	@Transient var isEnabled = false
		private set


	protected open fun onEnable() = Unit

	protected open fun onDisable() = Unit


	final override fun enable() {

		if (isEnabled) return

		onEnable()
		isEnabled = true
	}

	final override fun disable() {

		if (!isEnabled) return

		onDisable()
		isEnabled = false
	}

}