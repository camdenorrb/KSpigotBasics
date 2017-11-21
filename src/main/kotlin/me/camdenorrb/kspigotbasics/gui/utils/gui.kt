package me.camdenorrb.kspigotbasics.gui.utils

import me.camdenorrb.kspigotbasics.gui.types.ChestGui
import me.camdenorrb.kspigotbasics.gui.types.PlayerChestGui
import org.bukkit.entity.Player
import java.util.function.BiConsumer
import java.util.function.Consumer


@JvmSynthetic
fun chestGui(title: String = "", size: Int = 27, block: ChestGui.() -> Unit) = object : ChestGui(title, size) {

	override fun construct() = block(this)

}.apply { enable() }

@JvmSynthetic
fun playerChestGui(title: String = "", size: Int = 27, block: ChestGui.(Player) -> Unit) = object : PlayerChestGui(title, size) {

	override fun onConstruct(player: Player, chestGui: ChestGui) = block(chestGui, player)

}.apply { enable() }



// For the Java users ;)
@JvmOverloads
fun chestGui(title: String = "", size: Int = 27, consumer: Consumer<ChestGui>) = chestGui(title, size) {
	consumer.accept(this)
}

@JvmOverloads
fun playerChestGui(title: String = "", size: Int = 27, consumer: BiConsumer<ChestGui, Player>) = playerChestGui(title, size) {
	consumer.accept(this, it)
}