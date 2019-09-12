package technical.test.wefoxpokedex.ui.pokemonhunter.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import technical.test.wefoxpokedex.data.model.userCase.PokemonUserCaseModel
import kotlin.coroutines.CoroutineContext

interface PokemonHunterViewModel {

    val pokemonUsercase: LiveData<PokemonUserCaseModel>
    val isCatched: LiveData<Int>

    fun searchPokemon(dispatcher: CoroutineContext = Dispatchers.IO)
    fun catchPokemon(dispatcher: CoroutineContext = Dispatchers.IO)
    fun checkPokemonCatched()
}