package technical.test.pokedex.data.datasources.local

import kotlinx.coroutines.flow.Flow
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity

interface PokemonLocalDataSource {
    suspend fun getPokemonsCaught(): Flow<List<PokemonEntity>>
    suspend fun storePokemonCaught(pokemon: PokemonEntity)
    suspend fun setFreePokemonCaught(pokemonId: Int)
    suspend fun setFreeAllPokemon()

}