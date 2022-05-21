package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import git.dimitrikvirik.game2048.R
import git.dimitrikvirik.game2048.databinding.FragmentChooseNickNameBinding
import git.dimitrikvirik.game2048.presentation.vm.ChooseNickNameFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChooseNickNameFragment : Fragment() {

    lateinit var binding: FragmentChooseNickNameBinding
    private val vm: ChooseNickNameFragmentVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.contains("name") == true){
            findNavController().navigate(R.id.mainMenuFragment)
        }
        binding = FragmentChooseNickNameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.nickNameLiveData.observe(viewLifecycleOwner) {
            binding.nicknameET.setText(it)
        }
        binding.acceptBttn.setOnClickListener {
            val name = binding.nicknameET.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(context, "Please provide your nickname", Toast.LENGTH_LONG).show()
            } else {
                context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()
                    ?.putString("name", name)?.apply()
                context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()
                    ?.putInt("bestScore", 0)?.apply()
                findNavController().navigate(R.id.mainMenuFragment)
            }
        }
        binding.generateBttn.setOnClickListener {
            vm.getRandomNickName()
        }
    }
}