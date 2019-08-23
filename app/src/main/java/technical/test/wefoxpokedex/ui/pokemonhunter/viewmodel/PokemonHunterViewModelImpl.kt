package technical.test.wefoxpokedex.ui.pokemonhunter.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import technical.test.wefoxpokedex.data.repository.PokemonRepository
import kotlin.coroutines.CoroutineContext

class PokemonHunterViewModelImpl(private val repository: PokemonRepository) :
    ViewModel(), PokemonHunterViewModel {

    override val pokemonFounded: LiveData<PokemonModelView> = repository.pokemonFounded
    override val errorDataFound: LiveData<String> = repository.errorDataFound
    private val catched = MutableLiveData<Int>()
    override val isCatched: LiveData<Int> = catched

    override fun searchPokemon(dispatcher: CoroutineContext) {
        viewModelScope.launch(dispatcher) {
            repository.searchPokemon()
        }
    }

    override fun catchPokemon(dispatcher: CoroutineContext) {
        viewModelScope.launch(dispatcher) {
            repository.pokemonCatched()
            repository.getBackpack()
            repository.searchPokemon()
        }
    }

    override fun checkPokemonCatched() {
        repository.pokemonCatched.value?.let { pokemonList ->
            repository.pokemonFounded.value?.let { pokemonFound ->
                isCatched(pokemonList, pokemonFound)
            }
        }
    }

    private fun isCatched(pokemonList: List<PokemonModelView>,
                          pokemonFound: PokemonModelView) {
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
