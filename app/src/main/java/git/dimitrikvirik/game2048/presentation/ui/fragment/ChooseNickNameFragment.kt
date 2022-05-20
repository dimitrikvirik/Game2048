package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import git.dimitrikvirik.game2048.databinding.FragmentChooseNickNameBinding


class ChooseNickNameFragment : Fragment() {

    lateinit var binding: FragmentChooseNickNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            acceptBttn.setOnClickListener {
                val name = nicknameET.text.toString()
                if(name.isEmpty()){
                    Toast.makeText(context, "Please provide your nickname", Toast.LENGTH_LONG).show()
                }else{
                    context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()?.putString("name", name)?.apply()
                }
            }
            generateBttn.setOnClickListener {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseNickNameBinding.inflate(layoutInflater)
        return binding.root
    }


}