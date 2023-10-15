package technical.test.pokedex.data.datasources.remote.network.interfaces

import retrofit2.http.GET
import retrofit2.http.Path
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse

interface ApiInterface {

    @GET("api/v2/pokemon/{pokemon_id}")
    suspend fun fetchPokemon(@Path(value = "pokemon_id", encoded = true) pokemonId: Int): PokemonResponse
}