package technical.test.pokedex.ui

import technical.test.pokedex.domain.models.PokemonModel

sealed class PokemonViewStates {

    data object Idle: PokemonViewStates()
    data object Loading: PokemonViewStates()
    data class PokemonFounded(val pokemonFunded: PokemonModel): PokemonViewStates()
    data class PokemonCaughtList(val pokemonCaughtList: List<PokemonModel>): PokemonViewStates()
    data object BackpackEmpty: PokemonViewStates()
    data class ErrorDataFound(val errorMessage: String): PokemonViewStates()
}