package technical.test.pokedex.domain

import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.models.PokemonModel

class PokemonCaughtUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(pokemonModel: PokemonModel) =
        pokemonRepository.pokemonCaught(pokemonModel)
}