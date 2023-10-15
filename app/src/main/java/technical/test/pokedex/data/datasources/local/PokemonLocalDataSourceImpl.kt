package technical.test.pokedex.data.datasources.local

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.local.database.PokemonDao

class PokemonLocalDataSourceImpl(private val pokemonDao: PokemonDao): PokemonLocalDataSource {

    override suspend fun getPokemonsCatched(): Result<List<PokemonEntity>> =
        Result.success(pokemonDao.getAllPokemon())

    override suspend fun storePokemonCatched(pokemon: PokemonEntity) {
        pokemonDao.insertPokemon(pokemon = pokemon)
    }

    override suspend fun setFreePokemonCatched(pokemonId: Int) {
        pokemonDao.deletePokemon(pokemonId = pokemonId)
    }

    override suspend fun setFreeAllPokemon() {
        pokemonDao.deleteAllPokemon()
    }
}