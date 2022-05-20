package git.dimitrikvirik.game2048.presentation.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import git.dimitrikvirik.game2048.domain.repository.user.UserRepository
import kotlinx.coroutines.launch

class ChooseNickNameFragmentVM(
    private val userRepository: UserRepository
) : ViewModel() {
    val nickNameLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getRandomNickName() {
        viewModelScope.launch {
            val username = userRepository.getRandomUser().body()!!.results[0].login.username
            nickNameLiveData.value = username
            Log.d("USERNAME", username)
        }
    }
}