package technical.test.pokedex.data.persistence.datasource

import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.persistence.database.PokemonDao

class PersistencePokemonDataSource(private val pokemonDao: PokemonDao): PersistenceDataSource {

    override suspend fun getPokemonsCatched(): List<PokemonModel> =
        pokemonDao.getAllPokemon()

    override suspend fun storePokemonCatched(pokemon: PokemonModel) {
        pokemonDao.insertPokemon(pokemon = pokemon)
    }

    override suspend fun setFreePokemonCatched(pokemonId: Int) {
        pokemonDao.deletePokemon(pokemonId = pokemonId)
    }

    override suspend fun setFreeAllPokemon() {
        pokemonDao.deleteAllPokemon()
    }
}