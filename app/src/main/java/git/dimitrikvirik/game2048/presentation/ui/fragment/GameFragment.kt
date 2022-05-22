package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import git.dimitrikvirik.game2048.R
import git.dimitrikvirik.game2048.data.enum.Swipe
import git.dimitrikvirik.game2048.databinding.FragmentGameBinding
import git.dimitrikvirik.game2048.game.board.Direction
import git.dimitrikvirik.game2048.game.game2048.Game
import git.dimitrikvirik.game2048.game.game2048.newGame2048
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log2


class GameFragment : Fragment() {
    lateinit var nickname: String
    var bestScore: Int = 0
    var score = 0
    lateinit var binding: FragmentGameBinding
    private val vm: GameFragmentVM by viewModel()
    lateinit var game: Game
    lateinit var boardUI: List<List<TextView>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nickname = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
            ?.getString("name", "None") ?: "None"
        bestScore = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
            ?.getInt("bestScore", 0) ?: 0
        game = newGame2048()
        game.initialize()
        boardUI = with(binding) {
            listOf(
                listOf(grid00, grid01, grid02, grid03),
                listOf(grid10, grid11, grid12, grid13),
                listOf(grid20, grid21, grid22, grid23),
                listOf(grid30, grid31, grid32, grid33)
            )
        }

        context?.let {
            binding.boardGL.setOnTouchListener(object : OnSwipeTouchListener(it) {
                override fun onSwipeLeft() {
                    handleSwipe(Swipe.LEFT)
                }

                override fun onSwipeRight() {
                    handleSwipe(Swipe.RIGHT)
                }

                override fun onSwipeTop() {
                    handleSwipe(Swipe.TOP)
                }

                override fun onSwipeBottom() {
                    handleSwipe(Swipe.BOTTOM)
                }
            })
        }

        drawBoard()
    }

    private fun updateBestScore(score: Int) {
        context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()
            ?.putInt("bestScore", score)?.apply()
    }

    private fun handleSwipe(swipe: Swipe) {
        if (!game.hasWon() && game.canMove()) {
            val direction = when (swipe) {
                Swipe.LEFT -> Direction.LEFT
                Swipe.RIGHT -> Direction.RIGHT
                Swipe.BOTTOM -> Direction.DOWN
                Swipe.TOP -> Direction.UP
            }
            game.processMove(direction)
            drawBoard()
        }
    }

    private fun drawBoard() {
        score = 0
        var anyEmpty = false
        for (i in 1..4) {
            for (j in 1..4) {
                val textView = boardUI[j - 1][i - 1]
                if (game[i, j] != null) {
                    textView.visibility = View.VISIBLE
                    val num = game[i, j]!!
                    textView.text = num.toString()
                    textView.setBackgroundResource(
                        when (log2(num.toDouble())) {
                            1.0 -> R.drawable.background_grid_power1
                            2.0 -> R.drawable.background_grid_power2
                            3.0 -> R.drawable.background_grid_power3
                            4.0 -> R.drawable.background_grid_power4
                            5.0 -> R.drawable.background_grid_power5
                            6.0 -> R.drawable.background_grid_power6
                            7.0 -> R.drawable.background_grid_power7
                            8.0 -> R.drawable.background_grid_power8
                            11.0 -> R.drawable.background_grid_power11
                            else -> throw RuntimeException("Something went wrong in background calculation")
                        }
                    )
                    score += num
                    if (num == 2048) {
                        //TODO win game
                    }
                } else {
                    textView.visibility = View.INVISIBLE
                    anyEmpty = true
                }
            }
        }
        Log.d("123", score.toString())
        updateBestScore(score)
        if (!anyEmpty) {
            //TODO lose game
        }

    }


    private fun moveCell(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>) {

    }


}

open class OnSwipeTouchListener(ctx: Context) : View.OnTouchListener {

    private val gestureDetector: GestureDetector

    companion object {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100
    }

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }


    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}
}