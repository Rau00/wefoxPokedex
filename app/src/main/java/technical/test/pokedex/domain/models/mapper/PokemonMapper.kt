package technical.test.pokedex.domain.models.mapper

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse
import technical.test.pokedex.domain.models.PokemonModel

fun PokemonResponse.toModel(): PokemonModel =
    PokemonModel(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprites = sprites.frontDefault,
        baseExperience = baseExperience,
        types = types.map { it.type.name }
    )

fun PokemonModel.toEntity(): PokemonEntity =
    PokemonEntity(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprites = sprites,
        dateCaught = dateCaught,
        baseExperience = baseExperience,
        types = types
    )

fun PokemonEntity.toModel(): PokemonModel =
    PokemonModel(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprites = sprites,
        dateCaught = dateCaught,
        baseExperience = baseExperience,
        types = types
    )