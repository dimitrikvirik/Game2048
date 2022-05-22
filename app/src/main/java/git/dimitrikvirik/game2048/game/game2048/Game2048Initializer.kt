package git.dimitrikvirik.game2048.game.game2048

import git.dimitrikvirik.game2048.game.board.Cell
import git.dimitrikvirik.game2048.game.board.GameBoard
import java.util.*

interface Game2048Initializer<T> {
    fun nextValue(board: GameBoard<T?>): Pair<Cell, T>?
}

object RandomGame2048Initializer: Game2048Initializer<Int> {
    private val random = Random()
    private fun generateRandomStartValue(): Int =
            if (random.nextInt(10) == 9) 4 else 2

    /*
     * Generate a random value and a random cell (among free cells)
     * that given value should be added to.
     * The value should be 2 for 90% cases, and 4 for the rest of the cases.
     * Use the 'generateRandomStartValue' function above.
     * If the board is full return null.
     */
    override fun nextValue(board: GameBoard<Int?>): Pair<Cell, Int>? {
        val emptyCells = board.getAllCells().filter { board[it] == null}.toList()
        if(emptyCells.isEmpty()) return null

        val randomValue = generateRandomStartValue()
        val cellIndexToFill = (emptyCells.indices).shuffled().first()

        return emptyCells[cellIndexToFill] to randomValue
    }
}