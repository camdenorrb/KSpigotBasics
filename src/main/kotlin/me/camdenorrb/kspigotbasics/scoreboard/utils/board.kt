@file:JvmName("BoardUtils")

package me.camdenorrb.kspigotbasics.scoreboard.utils

import me.camdenorrb.kspigotbasics.scoreboard.Board
import me.camdenorrb.kspigotbasics.scoreboard.PlayerBoard
import org.bukkit.entity.Player
import java.util.function.BiConsumer
import java.util.function.Consumer


@JvmSynthetic
fun board(block: Board.() -> Unit) = Board().also(block)

@JvmSynthetic
fun playerBoard(construct: Board.(Player) -> Unit): PlayerBoard {

	val board = object : PlayerBoard() {

		override fun Board.onConstruct(player: Player) = construct(this, player)

	}

	return board.apply { enable() }
}



// For the Java users ;)

fun board(consumer: Consumer<Board>) = board {
	consumer.accept(this)
}

fun playerBoard(consumer: BiConsumer<Board, Player>) = playerBoard {
	consumer.accept(this, it)
}