package mx.dev.shellcore.android.dynamiccontent.ui.screen.main.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _nameList = MutableStateFlow(arrayListOf<String>())
    val nameList = _nameList.asStateFlow()

    private val _nameCapture = MutableStateFlow("")
    val nameCapture = _nameCapture.asStateFlow()

    fun onNameCaptureChange(name: String) {
        _nameCapture.value = name
    }

    fun onAddName() {
        _nameList.value.add(_nameCapture.value)
        _nameCapture.value = ""
    }
}