package technical.test.pokedex.data.repository

import androidx.lifecycle.LiveData
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel

interface PokemonRepository {
    val pokemonUsercase: LiveData<PokemonUserCaseModel>

    var pokemonFound: PokemonModel?
    var pokemonBackpack: List<PokemonModel>

    suspend fun searchPokemon()
    suspend fun getBackpack()
    fun pokemonCatched()
    fun setFreePokemon(id: Int)
    fun setFreeAllPokemon()
}