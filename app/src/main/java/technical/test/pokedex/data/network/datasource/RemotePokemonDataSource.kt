package technical.test.pokedex.data.network.datasource

import retrofit2.HttpException
import technical.test.pokedex.data.exceptions.RemoteDataNotFoundException
import technical.test.pokedex.data.network.interfaces.ApiInterface
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.source.PokemonResponse
import technical.test.pokedex.data.network.model.ResultData
import java.lang.IllegalArgumentException

class RemotePokemonDataSource(private val apiInterface: ApiInterface) :
    RemoteDataSource {

    override suspend fun getPokemon(pokemonId: Int): ResultData<PokemonResponse> =
        try {
            val response = apiInterface.fetchPokemon(pokemonId = pokemonId)
            ResultData.Success(response)
        } catch (e: HttpException) {
            ResultData.Error(e)
        }
}