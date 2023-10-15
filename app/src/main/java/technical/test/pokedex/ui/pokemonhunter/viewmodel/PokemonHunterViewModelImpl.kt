package technical.test.pokedex.ui.pokemonhunter.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.domain.PokemonUserCaseModel
import technical.test.pokedex.data.repository.PokemonRepository

class PokemonHunterViewModelImpl(private val repository: PokemonRepository) :
    ViewModel(), PokemonHunterViewModel {

    override val pokemonUsercase: LiveData<PokemonUserCaseModel> = repository.pokemonUsercase
    private val catched = MutableLiveData<Int>()
    override val isCaught: LiveData<Int> = catched

    override fun searchPokemon() {
        viewModelScope.launch {
            repository.searchPokemon()
        }
    }

    override fun catchPokemon() {
        viewModelScope.launch {
            repository.pokemonCaught()
            repository.getBackpack()
            repository.searchPokemon()
        }
    }

    override fun checkPokemonCaught() {
        repository.pokemonBackpack?.let { pokemonList ->
            repository.pokemonFound?.let { pokemonFound ->
                isCaught(pokemonList, pokemonFound)
            }
        }
    }

    private fun isCaught(
        pokemonList: List<PokemonEntity>,
        pokemonFound: PokemonEntity
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