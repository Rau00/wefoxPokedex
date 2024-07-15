package technical.test.pokedex.domain.models.mapper

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.local.entities.PokemonStatEntity
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonStatsResponse
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.domain.models.PokemonStats

fun PokemonResponse.toModel(): PokemonModel =
    PokemonModel(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprite = sprites.frontDefault,
        baseExperience = baseExperience,
        types = types.map { it.type.name },
        stats = stats.map { it.toModel() }
    )

fun PokemonModel.toEntity(): PokemonEntity =
    PokemonEntity(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprites = sprite,
        baseExperience = baseExperience,
        types = types,
        stats = stats.map { it.toEntity() }
    )

fun PokemonEntity.toModel(): PokemonModel =
    PokemonModel(
        id = id,
        name = name,
        weight = weight,
        height = height,
        order = order,
        sprite = sprites,
        dateCaught = dateCaught,
        baseExperience = baseExperience,
        types = types,
        stats = stats.map { it.toModel() }
    )

fun PokemonStatsResponse.toModel(): PokemonStats =
    PokemonStats(
        baseStat = baseStat,
        name = stat.name

    )

fun PokemonStats.toEntity(): PokemonStatEntity =
    PokemonStatEntity(
        baseStat = baseStat,
        name = name
    )

fun PokemonStatEntity.toModel(): PokemonStats =
    PokemonStats(
        baseStat = baseStat,
        name = name
    )