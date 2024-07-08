package technical.test.pokedex.ui

import androidx.compose.runtime.snapshots.SnapshotStateList
import technical.test.pokedex.domain.models.PokemonModel

sealed class PokemonViewStates {

    data object Idle: PokemonViewStates()
    data object Loading: PokemonViewStates()
    data class PokemonFounded(val pokemonFunded: PokemonModel): PokemonViewStates()
    data class PokemonCaughtList(val pokemonCaughtList: SnapshotStateList<PokemonModel>): PokemonViewStates()
    data object BackpackEmpty: PokemonViewStates()
    data class ErrorDataFound(val errorMessage: String): PokemonViewStates()
}