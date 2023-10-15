package technical.test.pokedex.data.repository

import androidx.lifecycle.LiveData
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.domain.PokemonUserCaseModel

interface PokemonRepository {
    val pokemonUsercase: LiveData<PokemonUserCaseModel>

    var pokemonFound: PokemonEntity?
    var pokemonBackpack: List<PokemonEntity>
    suspend fun searchPokemon()
    suspend fun getBackpack()
    suspend fun pokemonCatched()
    suspend fun setFreePokemon(id: Int)
    suspend fun setFreeAllPokemon()
}