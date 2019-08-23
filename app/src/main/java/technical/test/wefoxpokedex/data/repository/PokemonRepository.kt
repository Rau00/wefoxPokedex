package technical.test.wefoxpokedex.data.repository

import androidx.lifecycle.LiveData
import technical.test.wefoxpokedex.data.model.view.PokemonModelView

interface PokemonRepository {
    val pokemonFounded: LiveData<PokemonModelView>
    val pokemonCatched: LiveData<MutableList<PokemonModelView>>
    val backpackEmpty: LiveData<Boolean>
    val errorDataFound: LiveData<String>

    suspend fun searchPokemon()
    suspend fun getBackpack()
    fun pokemonCatched()
    fun setFreePokemon(id: Int)
    fun setFreeAllPokemon()
}