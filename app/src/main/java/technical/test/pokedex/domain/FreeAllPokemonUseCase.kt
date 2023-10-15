package technical.test.pokedex.domain

import technical.test.pokedex.data.repository.PokemonRepository

class FreeAllPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute() =
        pokemonRepository.freeAllPokemon()
}