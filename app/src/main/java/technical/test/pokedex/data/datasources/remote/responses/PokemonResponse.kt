package technical.test.pokedex.data.datasources.remote.responses

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: PokemonSpritesResponse,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val types: List<PokemonTypeResponse>
)

data class PokemonSpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String
)

data class PokemonTypeResponse(val type: PokemonTypeNameResponse)

data class PokemonTypeNameResponse(val name: String)