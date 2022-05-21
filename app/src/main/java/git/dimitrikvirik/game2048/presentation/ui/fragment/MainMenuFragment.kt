package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import git.dimitrikvirik.game2048.databinding.FragmentMainMenuBinding
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.MainMenuFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        binding.nickNameTV.text = nickname
    }
}