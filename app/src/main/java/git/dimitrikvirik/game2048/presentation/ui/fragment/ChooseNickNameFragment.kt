package git.dimitrikvirik.game2048.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        binding = FragmentChooseNickNameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.acceptBttn.setOnClickListener {
            val name = binding.nicknameET.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(context, "Please provide your nickname", Toast.LENGTH_LONG).show()
            } else {
                context?.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)?.edit()
                    ?.putString("name", name)?.apply()
            }
        }
        binding.generateBttn.setOnClickListener {
            vm.nickNameLiveData.observe(viewLifecycleOwner) {
                try {
                    binding.nicknameET.setText(it)
                } catch (ex: NumberFormatException) {

                }
            }
            vm.getRandomNickName()
        }
    }
}