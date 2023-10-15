package technical.test.pokedex.domain

import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.models.PokemonModel

class GetBackpackUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun execute(): Result<List<PokemonModel>> =
        pokemonRepository.getBackpack()
}