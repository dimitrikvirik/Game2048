package git.dimitrikvirik.game2048.game.game2048

import git.dimitrikvirik.game2048.game.board.Direction


interface Game {
    fun initialize()
    fun canMove(): Boolean
    fun hasWon(): Boolean
    fun processMove(direction: Direction)
    operator fun get(i: Int, j: Int): Int?
}
