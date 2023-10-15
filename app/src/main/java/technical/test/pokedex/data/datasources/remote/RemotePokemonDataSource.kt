package technical.test.pokedex.data.datasources.remote

import retrofit2.HttpException
import technical.test.pokedex.data.datasources.remote.network.exceptions.RemoteDataNotFoundException
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiInterface
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse

class RemotePokemonDataSource(private val apiInterface: ApiInterface) :
    RemoteDataSource {

    override suspend fun getPokemon(pokemonId: Int): Result<PokemonResponse> =
        try {
            val response = apiInterface.fetchPokemon(pokemonId = pokemonId)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        } catch (e: Throwable) {
            Result.failure(RemoteDataNotFoundException())
        }
}