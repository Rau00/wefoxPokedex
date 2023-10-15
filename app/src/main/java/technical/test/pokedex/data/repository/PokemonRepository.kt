package technical.test.pokedex.data.repository

import androidx.lifecycle.LiveData
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.domain.PokemonUserCaseModel
import technical.test.pokedex.domain.models.PokemonModel

interface PokemonRepository {

    suspend fun searchPokemon(pokemonId: Int): Result<PokemonModel>
    suspend fun getBackpack(): Result<List<PokemonModel>>
    suspend fun pokemonCaught(pokemonCaught: PokemonModel)
    suspend fun setFreePokemon(id: Int)
    suspend fun setFreeAllPokemon()
}