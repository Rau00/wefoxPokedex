package technical.test.pokedex.data.datasources.local

import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.local.database.PokemonDao
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor(private val pokemonDao: PokemonDao): PokemonLocalDataSource {

    override suspend fun getPokemonsCaught(): Result<List<PokemonEntity>> =
        Result.success(pokemonDao.getAllPokemon())

    override suspend fun storePokemonCaught(pokemon: PokemonEntity) {
        pokemonDao.insertPokemon(pokemon = pokemon)
    }

    override suspend fun setFreePokemonCaught(pokemonId: Int) {
        pokemonDao.deletePokemon(pokemonId = pokemonId)
    }

    override suspend fun setFreeAllPokemon() {
        pokemonDao.deleteAllPokemon()
    }
}