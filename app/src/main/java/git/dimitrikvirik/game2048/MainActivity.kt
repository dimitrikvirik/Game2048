package git.dimitrikvirik.game2048

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import git.dimitrikvirik.game2048.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animation = ObjectAnimator.ofFloat(binding.grid00, "translationY", 240f).apply { duration =  1000 }
        binding.grid00.setOnClickListener {
            animation.start()
        }
    }
}