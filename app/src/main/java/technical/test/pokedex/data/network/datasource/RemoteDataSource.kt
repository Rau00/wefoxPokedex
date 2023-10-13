package technical.test.pokedex.data.network.datasource

import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.source.PokemonResponse
import technical.test.pokedex.data.network.model.ResultData

interface RemoteDataSource {
    suspend fun getPokemon(pokemonId: Int): ResultData<PokemonResponse>
}