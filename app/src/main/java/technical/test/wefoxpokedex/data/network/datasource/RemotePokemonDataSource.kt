package technical.test.wefoxpokedex.data.network.datasource

import retrofit2.HttpException
import technical.test.wefoxpokedex.data.exceptions.RemoteDataNotFoundException
import technical.test.wefoxpokedex.data.network.interfaces.ApiInterface
import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.network.model.ResultData
import java.lang.IllegalArgumentException

class RemotePokemonDataSource(private val apiInterface: ApiInterface) :
    RemoteDataSource {

    override suspend fun getPokemon(pokemonId: Int): ResultData<PokemonModel> =
        try {
            val requestJob = apiInterface.fetchPokemon(pokemonId = pokemonId)
            val response = requestJob.await()
            ResultData.Success(response)
        } catch (e: HttpException) {
            throw e
        } catch (e: IllegalArgumentException) {
            throw e
        } catch (e: Throwable) {
            throw RemoteDataNotFoundException()
        }
}