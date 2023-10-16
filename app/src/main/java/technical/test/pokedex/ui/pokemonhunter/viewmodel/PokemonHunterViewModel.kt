package technical.test.pokedex.ui.pokemonhunter.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.PokemonCaughtUseCase
import technical.test.pokedex.domain.SearchPokemonUseCase
import technical.test.pokedex.domain.models.PokemonModel

class PokemonHunterViewModel(
    private val getBackpackUseCase: GetBackpackUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
    private val pokemonCaughtUseCase: PokemonCaughtUseCase
) :
    ViewModel() {

    private val _pokemonFound: MutableStateFlow<PokemonViewStates> =
        MutableStateFlow(PokemonViewStates.Idle)
    val pokemonFound = _pokemonFound.asStateFlow()

    private val _isPokemonCaught: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isPokemonCaught = _isPokemonCaught.asStateFlow()

    private var pokemon: PokemonModel? = null

    fun searchPokemon() {
        viewModelScope.launch {
            _pokemonFound.emit(PokemonViewStates.Loading)
            executePokemonSearchResult(searchPokemonUseCase())
        }
    }

    fun catchPokemon() {
        viewModelScope.launch {
            pokemon?.let {
                pokemonCaughtUseCase(it)
                executePokemonSearchResult(searchPokemonUseCase())
            }
        }
    }

    private suspend fun executePokemonSearchResult(result: Result<PokemonModel>) {
        result.onSuccess {
            pokemon = it
            _pokemonFound.emit(PokemonViewStates.PokemonFounded(it))
        }
            .onFailure {
                _pokemonFound.emit(PokemonViewStates.ErrorDataFound(it.stackTraceToString()))
            }
    }


    fun checkPokemonCaught() {
        viewModelScope.launch {
            pokemon?.let {
                getBackpackUseCase().onSuccess { pokemonList ->
                    isCaught(pokemonList, it)
                }
            }
        }
    }

private fun isCaught(
    pokemonList: List<PokemonModel>,
    pokemonFound: PokemonModel
) {
    viewModelScope.launch {
        for (pokemon in pokemonList) {
            if (pokemon.name == pokemonFound.name) {
                _isPokemonCaught.emit(true)
                break
            }
        }
    }
}
}
