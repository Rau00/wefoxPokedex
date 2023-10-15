package technical.test.pokedex.data.datasources.local

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity

interface PokemonLocalDataSource {
    suspend fun getPokemonsCaught(): Result<List<PokemonEntity>>
    suspend fun storePokemonCaught(pokemon: PokemonEntity)
    suspend fun setFreePokemonCaught(pokemonId: Int)
    suspend fun setFreeAllPokemon()

}