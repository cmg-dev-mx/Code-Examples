package mx.dev.shellcore.android.cache.ui.screen.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.android.cache.core.model.Pokemon
import mx.dev.shellcore.android.cache.core.request.RequestState
import mx.dev.shellcore.android.cache.repository.base.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _list = MutableStateFlow<RequestState<List<Pokemon>>>(RequestState.Idle)
    val listState = _list.asStateFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            _list.value = RequestState.Loading
            _list.value = repository.getPokemonList()
        }
    }
}