package technical.test.pokedex.ui.router

import kotlinx.serialization.Serializable
import technical.test.pokedex.domain.models.PokemonModel

interface Route

@Serializable
object Backpack : Route

@Serializable
object PokemonHunter : Route

@Serializable
data class PokemonDetail(val pokemonId: Int) : Route