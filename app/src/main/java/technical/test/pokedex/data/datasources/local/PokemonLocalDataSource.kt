package technical.test.pokedex.data.datasources.local

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity

interface PokemonLocalDataSource {
    suspend fun getPokemonsCatched(): Result<List<PokemonEntity>>
    suspend fun storePokemonCatched(pokemon: PokemonEntity)
    suspend fun setFreePokemonCatched(pokemonId: Int)
    suspend fun setFreeAllPokemon()

}