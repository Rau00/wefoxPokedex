package technical.test.pokedex.domain

import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.models.PokemonModel

class SearchPokemonUseCase (private val pokemonRepository: PokemonRepository) {

    companion object {
        private const val RANDOM_MIN = 1
        private const val RANDOM_MAX = 1000
    }

    suspend fun execute(): Result<PokemonModel> =
        pokemonRepository.searchPokemon((RANDOM_MIN..RANDOM_MAX).random())
}