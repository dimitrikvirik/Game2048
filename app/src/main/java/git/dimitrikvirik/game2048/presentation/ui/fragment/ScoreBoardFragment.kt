package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import git.dimitrikvirik.game2048.R
import git.dimitrikvirik.game2048.databinding.FragmentMainMenuBinding
import git.dimitrikvirik.game2048.databinding.FragmentScoreBoardBinding
import git.dimitrikvirik.game2048.presentation.ui.adapter.ScoreBoardRVAdapter
import git.dimitrikvirik.game2048.presentation.vm.GameFragmentVM
import git.dimitrikvirik.game2048.presentation.vm.ScoreBoardFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel


class ScoreBoardFragment : Fragment() {
    private lateinit var binding: FragmentScoreBoardBinding
    private val vm: ScoreBoardFragmentVM by viewModel()

    private lateinit var nickname: String
    var bestScore: Int = 0

    private lateinit var scoreBoardRVAdapter: ScoreBoardRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scoreBoardRVAdapter = ScoreBoardRVAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scoreBoardRV.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.scoreBoardRV.adapter = scoreBoardRVAdapter
        getUserInfo()
        scoreBoardRVAdapter.updateList(getScoreBoardList())

        binding.resetBttn.setOnClickListener {
            context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()?.putInt("bestScore", 0)?.apply()
            getUserInfo()
            scoreBoardRVAdapter.updateList(getScoreBoardList())
        }
    }

    fun getScoreBoardList(): List<Pair<String, Int>>{
        return listOf("Krivik" to 24300, "Nandemonaya" to 29500, nickname to bestScore).sortedWith(compareBy { it.second }).reversed()
    }

    fun getUserInfo(){
        nickname = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getString("name", "None")?: "None"
        bestScore = context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.getInt("bestScore", 0)?: 0
    }

}