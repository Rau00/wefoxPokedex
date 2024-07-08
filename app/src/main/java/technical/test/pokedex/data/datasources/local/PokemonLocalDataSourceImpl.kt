package technical.test.pokedex.data.datasources.local

import kotlinx.coroutines.flow.Flow
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.local.database.PokemonDao
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor(private val pokemonDao: PokemonDao): PokemonLocalDataSource {

    override suspend fun getPokemonsCaught(): Flow<List<PokemonEntity>> =
        pokemonDao.getAllPokemon()

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