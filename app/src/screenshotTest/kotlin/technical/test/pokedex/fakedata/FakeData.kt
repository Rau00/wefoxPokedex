package technical.test.pokedex.fakedata

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.local.entities.PokemonStatEntity
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonSpritesResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonStatsResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonTypeNameResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonTypeResponse
import technical.test.pokedex.data.datasources.remote.responses.StatsResponse
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.domain.models.PokemonStats


val fakePokemonModel = PokemonModel(
    id = 31,
    name = "pikachu",
    weight = 7,
    height = 40,
    order = 35,
    sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
    baseExperience = 34,
    types = listOf("electrico"),
    stats = listOf(
        PokemonStats(
            baseStat = 35,
            name = "hp"
        ),
        PokemonStats(
            baseStat = 55,
            name = "attack"
        ),
        PokemonStats(
            baseStat = 40,
            name = "defense"
        ),
        PokemonStats(
            baseStat = 50,
            name = "special-attack"
        ),
        PokemonStats(
            baseStat = 50,
            name = "special_defense"
        ),
        PokemonStats(
            baseStat = 90,
            name = "speed"
        )
    )
)