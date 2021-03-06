package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import git.dimitrikvirik.game2048.R
import git.dimitrikvirik.game2048.databinding.FragmentMainMenuBinding
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.MainMenuFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class MainMenuFragment : Fragment() {
    lateinit var nickname: String
    lateinit var binding: FragmentMainMenuBinding
    private val vm: MainMenuFragmentVM by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nickname = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getString("name", "None")?: "None"
        with(binding){
            nickNameTV.text = "Welcome, " + nickname
            scoreBoardBttn.setOnClickListener {
                findNavController().navigate(R.id.scoreBoardFragment)
            }
            playBttn.setOnClickListener {
                findNavController().navigate(R.id.gameFragment)
            }

            userBttn.setOnClickListener{
                context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()?.remove("name")?.apply()
                findNavController().navigate(R.id.chooseNickNameFragment)
            }

            exitBttn.setOnClickListener {
                exitProcess(0);
            }

        }

    }
}