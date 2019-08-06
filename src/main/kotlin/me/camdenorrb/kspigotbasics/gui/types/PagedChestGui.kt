package me.camdenorrb.kspigotbasics.gui.types

import me.camdenorrb.kspigotbasics.ext.meta
import me.camdenorrb.kspigotbasics.gui.Gui
import me.camdenorrb.kspigotbasics.struct.GREEN
import me.camdenorrb.kspigotbasics.struct.RED
import org.bukkit.Material.FEATHER
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack



open class PagedChestGui : Gui {

	val pages = mutableListOf<ChestGui>()


	open val lastPagePointer = ItemStack(FEATHER).meta {
		setDisplayName("${GREEN}Last Page")
	}

	open val nextPagePointer = ItemStack(FEATHER).meta {
		setDisplayName("${RED}Next Page")
	}

	
	override fun open(target: Player) {
		pages.first().open(target)
	}


	operator fun get(index: Int) = pages[index]


	fun add(gui: ChestGui) {
		pages.add(gui)
	}

	fun add(gui: ChestGui, index: Int) {
		pages.add(index, gui)
	}


	fun rem(index: Int) {

	}

	fun rem(page: ChestGui) {

	}


	@JvmOverloads
	fun createPage(index: Int? = null) {

	}

}