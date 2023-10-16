package technical.test.pokedex.ui.pokemondetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import technical.test.pokedex.domain.models.PokemonModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel  @Inject constructor(): ViewModel() {

    private val _pokemon: MutableStateFlow<PokemonModel?> = MutableStateFlow(null)
    val pokemon = _pokemon.asStateFlow()

    fun setupPokemonDetail(pokemon: PokemonModel) {
        viewModelScope.launch {
            _pokemon.emit(pokemon)
        }
    }
}
