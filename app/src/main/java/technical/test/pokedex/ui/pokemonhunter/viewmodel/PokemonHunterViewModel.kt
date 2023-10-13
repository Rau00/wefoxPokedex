package technical.test.pokedex.ui.pokemonhunter.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel
import kotlin.coroutines.CoroutineContext

interface PokemonHunterViewModel {

    val pokemonUsercase: LiveData<PokemonUserCaseModel>
    val isCatched: LiveData<Int>

    fun searchPokemon()
    fun catchPokemon()
    fun checkPokemonCatched()
}