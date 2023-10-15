package technical.test.pokedex.domain

import technical.test.pokedex.data.repository.PokemonRepository

class FreePokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute(pokemonId: Int) =
        pokemonRepository.freePokemon(pokemonId)
}