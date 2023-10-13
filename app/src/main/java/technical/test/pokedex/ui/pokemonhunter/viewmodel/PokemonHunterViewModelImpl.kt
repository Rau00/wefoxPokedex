package technical.test.pokedex.ui.pokemonhunter.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel
import technical.test.pokedex.data.repository.PokemonRepository
import kotlin.coroutines.CoroutineContext

class PokemonHunterViewModelImpl(private val repository: PokemonRepository) :
    ViewModel(), PokemonHunterViewModel {

    override val pokemonUsercase: LiveData<PokemonUserCaseModel> = repository.pokemonUsercase
    private val catched = MutableLiveData<Int>()
    override val isCatched: LiveData<Int> = catched

    override fun searchPokemon() {
        viewModelScope.launch {
            repository.searchPokemon()
        }
    }

    override fun catchPokemon() {
        viewModelScope.launch {
            repository.pokemonCatched()
            repository.getBackpack()
            repository.searchPokemon()
        }
    }

    override fun checkPokemonCatched() {
        repository.pokemonBackpack?.let { pokemonList ->
            repository.pokemonFound?.let { pokemonFound ->
                isCatched(pokemonList, pokemonFound)
            }
        }
    }

    private fun isCatched(
        pokemonList: List<PokemonModel>,
        pokemonFound: PokemonModel
    ) {
        for (pokemon in pokemonList) {
            if (pokemon.name == pokemonFound.name) {
                catched.value = View.GONE
                break
            } else {
                catched.value = View.VISIBLE
            }
        }
    }
}
