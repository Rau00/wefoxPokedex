package technical.test.pokedex.data.model.userCase

import technical.test.pokedex.data.model.view.PokemonModelView

sealed class PokemonUserCaseModel {
    data class PokemonFounded(val pokemonFunded: PokemonModelView): PokemonUserCaseModel()
    data class PokemonsCatched(val pokemonsCatched: MutableList<PokemonModelView>): PokemonUserCaseModel()
    data class BackpackEmpty(val isEmpty: Boolean): PokemonUserCaseModel()
    data class ErrorDataFound(val errorMessage: String): PokemonUserCaseModel()
}