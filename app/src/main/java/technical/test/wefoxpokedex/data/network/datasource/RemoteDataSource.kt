package technical.test.wefoxpokedex.data.network.datasource

import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.network.model.ResultData

interface RemoteDataSource {
    suspend fun getPokemon(pokemonId: Int): ResultData<PokemonModel>
}