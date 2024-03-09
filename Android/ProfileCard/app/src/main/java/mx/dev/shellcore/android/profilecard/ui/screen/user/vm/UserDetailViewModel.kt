package mx.dev.shellcore.android.profilecard.ui.screen.user.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.dev.shellcore.android.profilecard.core.model.UserProfile
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor() : ViewModel() {

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
        _user.value = UserProfile(
            id = 1,
            name = "Michaela Runnings",
            status = true,
            drawableUrl = "https://images.unsplash.com/photo-1485290334039-a3c69043e517?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
        )
    }
}