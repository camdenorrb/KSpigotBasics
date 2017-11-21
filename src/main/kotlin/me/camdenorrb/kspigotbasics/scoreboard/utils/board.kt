package me.camdenorrb.kspigotbasics.scoreboard.utils

import me.camdenorrb.kspigotbasics.scoreboard.Board
import me.camdenorrb.kspigotbasics.scoreboard.PlayerBoard
import org.bukkit.entity.Player


fun board(block: Board.() -> Unit) = Board().also(block)


fun playerBoard(construct: Board.(Player) -> Unit): PlayerBoard {

	val board = object : PlayerBoard() {

		override fun Board.onConstruct(player: Player) = construct(this, player)

	}

	return board.apply { enable() }
}