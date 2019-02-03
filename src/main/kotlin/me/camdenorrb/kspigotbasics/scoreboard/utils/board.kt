@file:JvmName("BoardUtils")

package me.camdenorrb.kspigotbasics.scoreboard.utils

import me.camdenorrb.kspigotbasics.scoreboard.Board
import me.camdenorrb.kspigotbasics.scoreboard.PlayerBoard
import me.camdenorrb.kspigotbasics.scoreboard.PlayerBoards
import org.bukkit.entity.Player
import java.util.function.Consumer


@JvmSynthetic
fun board(block: Board.() -> Unit) = Board().also(block)

@JvmSynthetic
fun playerBoards(construct: PlayerBoard.(Player) -> Unit): PlayerBoards {

	val board = object : PlayerBoards() {

		override fun PlayerBoard.onConstruct() = construct(this, player)

	}

	return board.apply { enable() }
}



// For the Java users ;)

/**
 * 
 *
 * @param consumer
 */
@JvmName("board")
fun jBoard(consumer: Consumer<Board>) = board {
	consumer.accept(this)
}

@JvmName("playerBoards")
fun jPlayerBoards(consumer: Consumer<PlayerBoard>) = playerBoards {
	consumer.accept(this)
}