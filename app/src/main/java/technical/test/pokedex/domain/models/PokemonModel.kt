package technical.test.pokedex.domain.models

data class PokemonModel(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: String,
    val baseExperience: Int,
    val types: List<String>,
    val dateCaught: String = ""
)