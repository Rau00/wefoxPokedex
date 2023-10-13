package technical.test.pokedex.data.model.source

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: PokemonSprites,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val types: List<PokemonType>
)
