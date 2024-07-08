package technical.test.pokedex.domain

import kotlinx.coroutines.flow.Flow
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.models.PokemonModel

class GetBackpackUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(): Flow<List<PokemonModel>> =
        pokemonRepository.getBackpack()
}