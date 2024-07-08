package technical.test.pokedex.data.repository

import kotlinx.coroutines.flow.Flow
import technical.test.pokedex.domain.models.PokemonModel

interface PokemonRepository {

    suspend fun searchPokemon(pokemonId: Int): Result<PokemonModel>
    suspend fun getBackpack(): Flow<List<PokemonModel>>
    suspend fun pokemonCaught(pokemonCaught: PokemonModel)
    suspend fun freePokemon(id: Int)
    suspend fun freeAllPokemon()
}