package me.camdenorrb.kspigotbasics.cache

import me.camdenorrb.kspigotbasics.bar.ActionBar
import me.camdenorrb.kspigotbasics.ext.skull
import me.camdenorrb.kspigotbasics.gui.utils.playerChestGui
import me.camdenorrb.kspigotbasics.player.KBasicPlayer
import me.camdenorrb.kspigotbasics.struct.server
import me.camdenorrb.kspigotbasics.struct.spigotBasics
import me.camdenorrb.kspigotbasics.title.Title
import me.camdenorrb.kspigotbasics.types.modules.ListeningModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority.HIGHEST
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


object KBasicPlayerCache : ListeningModule() {

	val basicPlayers = mutableMapOf<Player, KBasicPlayer>()


	override fun onStart() = server.onlinePlayers.forEach { join(it) }

	override fun onStop() = basicPlayers.clear()


	fun join(player: Player) {
		basicPlayers[player] = KBasicPlayer(player)
		ActionBar("Meowwwwwwwwwwwwww").send(player)
		Title("Meoeeeeeewoowww", "MEWWWWWw", 20, 20, 20).send(player)

		server.scheduler.runTaskLater(spigotBasics, {
			playerChestGui { it.skull().setSlots(0..26) }.open(player)
		}, 20L)
		/*playerChestGui {
			//it.skull().setSlot(0)
		}.open(player)*/
		/*

		board {

			createSideBar("Meow", 2) {
				text("Meow")
				text("Hiss")
			}

		}.open(player)
		*/

	}

	fun leave(player: Player) {
		basicPlayers.remove(player)
	}


	@EventHandler(priority = HIGHEST)
	fun onJoin(event: PlayerJoinEvent) = join(event.player)

	@EventHandler
	fun onLeave(event: PlayerQuitEvent) = leave(event.player)

}