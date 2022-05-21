package git.dimitrikvirik.game2048.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import git.dimitrikvirik.game2048.databinding.ScoreBoardRvItemCardBinding

class ScoreBoardRVAdapter: RecyclerView.Adapter<ScoreBoardRVAdapter.ViewHolder>() {

    private var scoreList: List<Pair<String, Int>> = listOf()

    class ViewHolder(private val binding: ScoreBoardRvItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pair<String, Int>) {
            with(binding){
                nicknameTV.text = data.first
                scoreTV.text = data.second.toString()
            }
        }
    }

    fun updateList(newScoreList: List<Pair<String, Int>>) {
        scoreList = newScoreList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ScoreBoardRvItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(scoreList[position])
    }

    override fun getItemCount() = scoreList.size

}