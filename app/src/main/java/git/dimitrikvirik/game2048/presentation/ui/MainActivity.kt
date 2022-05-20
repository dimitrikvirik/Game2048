package git.dimitrikvirik.game2048.presentation.ui

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import git.dimitrikvirik.game2048.databinding.ActivityMainBinding
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

   lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        startKoin{
            modules(git.dimitrikvirik.game2048.domain.koin.modules)
            androidContext(applicationContext)
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val animation = ObjectAnimator.ofFloat(binding.grid00, "translationY", 240f).apply { duration =  1000 }
//        binding.grid00.setOnClickListener {
//            animation.start()
//        }
    }
}