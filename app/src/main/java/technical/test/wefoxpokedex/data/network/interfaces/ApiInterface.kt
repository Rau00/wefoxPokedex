package technical.test.wefoxpokedex.data.network.interfaces

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import technical.test.wefoxpokedex.data.model.source.PokemonModel

interface ApiInterface {

    @GET("api/v2/pokemon/{pokemon_id}")
    fun fetchPokemon(@Path(value = "pokemon_id", encoded = true) pokemonId: Int): Deferred<PokemonModel>
}