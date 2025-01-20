package mx.dev.cmg.android.jetai.authentication.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jetai.Graph
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.utils.ext.collectAndHandle

class SignUpVieModel(
    private val repository: AuthRepository = Graph.authRepository
) : ViewModel() {

    var signUpState by mutableStateOf(SignUpState())
        private set

    fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnFirstNameChanged -> {
                signUpState = signUpState.copy(firstName = event.firstName)
            }

            is SignUpEvent.OnLastNameChanged -> {
                signUpState = signUpState.copy(lastName = event.lastName)
            }

            is SignUpEvent.OnEmailChanged -> {
                signUpState = signUpState.copy(email = event.email)
            }

            is SignUpEvent.OnPasswordChanged -> {
                signUpState = signUpState.copy(password = event.password)
            }

            is SignUpEvent.OnConfirmPasswordChanged -> {
                signUpState = signUpState.copy(confirmPassword = event.confirmPassword)
            }

            is SignUpEvent.OnAgreeTermsChanged -> {
                signUpState = signUpState.copy(agreeTerms = event.agreeTerms)
            }

            is SignUpEvent.OnSignUpClicked -> {
                // TODO: Create user
            }

            is SignUpEvent.OnVerificationEmailSent -> {
                signUpState = signUpState.copy(isVerificationEmailSent = false)
            }
        }
    }

    private fun validateSignUpForm() = signUpState.run {
        firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && email.isNotEmpty()
                && password.isNotEmpty()
                && confirmPassword.isNotEmpty()
                && agreeTerms
    }

    private fun createUser() = viewModelScope.launch {
        try {
            val isNotSamePassword = signUpState.password != signUpState.confirmPassword
            if (!validateSignUpForm()) throw IllegalArgumentException("Fields can not be empty")
            if (isNotSamePassword) throw IllegalArgumentException("Passwords do not match")

            signUpState = signUpState.copy(isLoading = true, loginErrorMsg = null)
            repository.createUser(signUpState.email, signUpState.password).collectAndHandle(
                onError = {
                    signUpState = signUpState.copy(isLoading = false, loginErrorMsg = it?.message)
                    throw it ?: Throwable("Unknown error")
                },
                onLoading = {
                    signUpState = signUpState.copy(isLoading = true)
                }
            ) {
                signUpState = signUpState.copy(isSuccessLogin = true)
                sendVerificationEmail()
            }
        } catch (e: Exception) {
            signUpState = signUpState.copy(loginErrorMsg = e.localizedMessage)
        } finally {
            signUpState = signUpState.copy(isLoading = false)
        }
    }

    private fun sendVerificationEmail() = repository.sendVerificationEmail(
        onSuccess = {
            signUpState = signUpState.copy(isVerificationEmailSent = true)
        },
        onError = {
            throw it ?: Throwable("Unknown error")
        }
    )
}

data class SignUpState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val agreeTerms: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val isVerificationEmailSent: Boolean = false,
    val loginErrorMsg: String? = null,
)

sealed class SignUpEvent {
    data class OnFirstNameChanged(val firstName: String) : SignUpEvent()
    data class OnLastNameChanged(val lastName: String) : SignUpEvent()
    data class OnEmailChanged(val email: String) : SignUpEvent()
    data class OnPasswordChanged(val password: String) : SignUpEvent()
    data class OnConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent()
    data class OnAgreeTermsChanged(val agreeTerms: Boolean) : SignUpEvent()
    data object OnSignUpClicked : SignUpEvent()
    data object OnVerificationEmailSent : SignUpEvent()
}