package mx.dev.cmg.android.jetai.photo_reasoning

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.dev.cmg.android.jetai.Graph
import mx.dev.cmg.android.jetai.data.repository.AuthRepository
import mx.dev.cmg.android.jetai.data.repository.PhotoReasoningRepository
import mx.dev.cmg.android.jetai.utils.ext.collectAndHandle

class PhotoReasoningViewModel(
    private val photoReasoningRepository: PhotoReasoningRepository = Graph.photoReasoningRepository,
    private val authRepository: AuthRepository = Graph.authRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<PhotoReasoningUiState> = MutableStateFlow(PhotoReasoningUiState.Initial)
    val uiState = _uiState.asStateFlow()

    var currentUser: FirebaseUser? by mutableStateOf(null)
        private set

    init {
        viewModelScope.launch {
            authRepository.currentUser.collectLatest {
                currentUser = it
            }
        }
    }

    fun reason(userInput: String, selectedImages: List<Bitmap>) {
        viewModelScope.launch {
            photoReasoningRepository.reason(userInput, selectedImages).collectAndHandle(
                onError = { error ->
                    _uiState.update {
                        PhotoReasoningUiState.Error(error?.localizedMessage ?: "")
                    }
                },
                onLoading = {
                    _uiState.update {
                        PhotoReasoningUiState.Loading
                    }
                }
            ) { output ->
                _uiState.update {
                    PhotoReasoningUiState.Success(output)
                }
            }
        }
    }
}