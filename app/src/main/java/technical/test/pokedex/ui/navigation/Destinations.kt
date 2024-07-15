package technical.test.pokedex.ui.navigation

import kotlinx.serialization.Serializable

interface Route

@Serializable
object Backpack : Route

@Serializable
object PokemonHunter : Route

@Serializable
data class PokemonDetail(val pokemonId: Int) : Route