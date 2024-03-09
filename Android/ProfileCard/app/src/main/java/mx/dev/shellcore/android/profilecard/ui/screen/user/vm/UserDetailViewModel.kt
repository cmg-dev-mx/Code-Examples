package mx.dev.shellcore.android.profilecard.ui.screen.user.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.profilecard.core.model.UserProfile
import mx.dev.shellcore.android.profilecard.core.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow(
        UserProfile(
            id = 0,
            name = "",
            status = false,
            drawableUrl = ""
        )
    )
    val user = _user.asStateFlow()

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            _user.value = repository.getUserById(userId)
        }
    }
}