package technical.test.pokedex.data.model.source.mapper

import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.source.PokemonResponse

fun PokemonResponse.toModel(): PokemonModel =
    PokemonModel(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprites = sprites,
        baseExperience = baseExperience,
        types = types
    )