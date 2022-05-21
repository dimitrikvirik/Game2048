package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import git.dimitrikvirik.game2048.R
import git.dimitrikvirik.game2048.databinding.FragmentMainMenuBinding
import git.dimitrikvirik.game2048.databinding.FragmentScoreBoardBinding
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.ScoreBoardFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel


class ScoreBoardFragment : Fragment() {
    lateinit var nickname: String
    var bestScore: Int = 0
    lateinit var binding: FragmentScoreBoardBinding
    private val vm: ScoreBoardFragmentVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nickname = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getString("name", "None")?: "None"
        bestScore = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getInt("bestScore", 0)?: 0
    }

}