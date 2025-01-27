package mx.dev.cmg.android.jetai.authentication.login

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jetai.Graph
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.GoogleAuthClient
import mx.dev.cmg.android.jetai.utils.ext.collectAndHandle
import mx.dev.cmg.android.jetai.utils.isValidEmail

class LoginViewModel(
    private val repository: AuthRepository = Graph.authRepository,
    private val googleAuthClient: GoogleAuthClient = Graph.googleAuthClient
) : ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    companion object {
        private const val TAG = "loginVm"
    }

    init {
        viewModelScope.launch {
            repository.currentUser.collectLatest {
                loginState = loginState.copy(currentUser = it)
            }
        }
    }

    fun loginEvent(loginEvent: LoginEvents) {
        when (loginEvent) {
            is LoginEvents.OnEmailChanged -> {
                loginState = loginState.copy(email = loginEvent.email)
            }

            is LoginEvents.OnPasswordChanged -> {
                loginState = loginState.copy(password = loginEvent.password)
            }

            is LoginEvents.Login -> {
                login()
            }

            is LoginEvents.OnResendVerification -> {
                resendVerification()
            }

            is LoginEvents.SignInWithGoogle -> {
                viewModelScope.launch {
                    googleAuthClient.signInWithIntent(loginEvent.intent)
                        .collectAndHandle(
                            onError = {
                                loginState = loginState.copy(loginErrorMsg = it?.localizedMessage)
                            },
                            onLoading = {
                                loginState = loginState.copy(isLoading = true)
                            }
                        ) {
                            hasNotVerifiedThrowError()
                            loginState = loginState.copy(isSuccessLogin = true, isLoading = false)
                        }
                }
            }
        }
    }

    private fun login() = viewModelScope.launch {
        try {
            loginState = loginState.copy(loginErrorMsg = null)
            if (validateLoginForm()) {
                throw IllegalArgumentException("Password or Email can not be empty")
            }
            if (!isValidEmail(loginState.email)) {
                throw IllegalArgumentException("Invalid Email address")
            }
            loginState = loginState.copy(isLoading = true)
            repository.login(loginState.email, loginState.password)
                .collectAndHandle(
                    onLoading = {
                        loginState = loginState.copy(isLoading = true)
                    },
                    onError = {
                        loginState = loginState.copy(
                            isSuccessLogin = false,
                            loginErrorMsg = it?.localizedMessage,
                            isLoading = false
                        )
                    }
                ) {
                    hasNotVerifiedThrowError()
                    loginState = loginState.copy(
                        isSuccessLogin = true,
                        isLoading = false
                    )
                }
        } catch (e: Exception) {
            loginState = loginState.copy(
                loginErrorMsg = e.localizedMessage
            )
        } finally {
            loginState = loginState.copy(isLoading = false)
        }
    }

    private fun validateLoginForm() = loginState.email.isNotBlank()
            && loginState.password.isNotBlank()

    private fun hasNotVerifiedThrowError() {
        if (!repository.hasVerifiedUser()) {
            loginState = loginState.copy(showResendBtn = true)
            throw IllegalArgumentException(
                """
                We've sent a verification link to your email.
                Please check your inbox and clic the link to activate your account.${loginState.currentUser?.email}
            """.trimIndent()
            )
        }
    }

    private fun resendVerification() {
        try {
            repository.sendVerificationEmail(onSuccess = {
                loginState = loginState.copy(showResendBtn = false)
            }, onError = {
                loginState = loginState.copy(
                    loginErrorMsg = it?.localizedMessage
                )
            })
        } catch (e: Exception) {
            loginState = loginState.copy(
                loginErrorMsg = e.localizedMessage
            )
            e.printStackTrace()
        }
    }
}

data class LoginState(
    val loginErrorMsg: String? = null,
    val isLoading: Boolean = false,
    val isValidEmailAddress: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isSuccessLogin: Boolean = false,
    val currentUser: FirebaseUser? = null,
    val isUserVerified: Boolean? = null,
    val showResendBtn: Boolean = false
)

sealed class LoginEvents {
    data class OnEmailChanged(val email: String) : LoginEvents()
    data class OnPasswordChanged(val password: String) : LoginEvents()
    data object Login : LoginEvents()
    data object OnResendVerification : LoginEvents()
    data class SignInWithGoogle(val intent: Intent) : LoginEvents()
}