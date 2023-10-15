package technical.test.pokedex.ui

import technical.test.pokedex.data.models.view.PokemonModelView

sealed class PokemonViewStates {
    data class PokemonFounded(val pokemonFunded: PokemonModelView): PokemonViewStates()
    data class PokemonsCaught(val pokemonsCaught: MutableList<PokemonModelView>): PokemonViewStates()
    data class BackpackEmpty(val isEmpty: Boolean): PokemonViewStates()
    data class ErrorDataFound(val errorMessage: String): PokemonViewStates()
}