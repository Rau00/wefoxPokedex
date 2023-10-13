package technical.test.pokedex.data.network.interfaces

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.source.PokemonResponse

interface ApiInterface {

    @GET("api/v2/pokemon/{pokemon_id}")
    suspend fun fetchPokemon(@Path(value = "pokemon_id", encoded = true) pokemonId: Int): PokemonResponse
}