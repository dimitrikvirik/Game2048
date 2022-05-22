package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import git.dimitrikvirik.game2048.data.enum.Swipe
import git.dimitrikvirik.game2048.databinding.FragmentGameBinding
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameFragment : Fragment() {
    lateinit var nickname: String
    var bestScore: Int = 0
    lateinit var binding: FragmentGameBinding
    private val vm: GameFragmentVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nickname = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getString("name", "None")?: "None"
        bestScore = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getInt("bestScore", 0)?: 0
        context?.let {
            binding.boardGL.setOnTouchListener(object: OnSwipeTouchListener(it) {
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
    }

    private fun updateBestScore(score: Int){
        if(score > bestScore){
            context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()?.putInt("bestScore", score)?.apply()
        }
    }

    private fun handleSwipe(swipe: Swipe){
        when(swipe){
            Swipe.LEFT-> {

            }
            Swipe.RIGHT-> {

            }
            Swipe.BOTTOM-> {

            }
            Swipe.TOP-> {

            }
        }
    }

    private fun moveCell(moveFrom: Pair<Int, Int>, moveTo: Pair<Int, Int>){

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

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
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