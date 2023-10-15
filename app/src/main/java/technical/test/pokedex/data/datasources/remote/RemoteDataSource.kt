package technical.test.pokedex.data.datasources.remote

import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse

interface RemoteDataSource {
    suspend fun getPokemon(pokemonId: Int): Result<PokemonResponse>
}