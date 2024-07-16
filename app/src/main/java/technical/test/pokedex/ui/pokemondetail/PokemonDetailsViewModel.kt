package technical.test.pokedex.ui.pokemondetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.models.PokemonModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getBackpackUseCase: GetBackpackUseCase
) : ViewModel() {

    fun getPokemonDetails(pokemonId: String): PokemonModel? {
        var pokemon: PokemonModel? = null
        runBlocking {
            val pokemonList = getBackpackUseCase().first()
            pokemon = pokemonList.find { it.id == pokemonId.toInt() }
        }
        return pokemon
    }
}