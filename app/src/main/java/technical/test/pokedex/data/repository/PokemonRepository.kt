package technical.test.pokedex.data.repository

import technical.test.pokedex.domain.models.PokemonModel

interface PokemonRepository {

    suspend fun searchPokemon(pokemonId: Int): Result<PokemonModel>
    suspend fun getBackpack(): Result<List<PokemonModel>>
    suspend fun pokemonCaught(pokemonCaught: PokemonModel)
    suspend fun freePokemon(id: Int)
    suspend fun freeAllPokemon()
}