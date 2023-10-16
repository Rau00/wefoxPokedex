package technical.test.pokedex.data.repository

import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.domain.models.mapper.toEntity
import technical.test.pokedex.domain.models.mapper.toModel

class PokemonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val daoDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun searchPokemon(pokemonId: Int): Result<PokemonModel> =
        remoteDataSource.getPokemon(pokemonId).map { it.toModel() }


    override suspend fun getBackpack(): Result<List<PokemonModel>> =
        daoDataSource.getPokemonsCaught().map { pokemonList ->
            sortByOrder(pokemonList.map { it.toModel() })
        }

    override suspend fun pokemonCaught(pokemonCaught: PokemonModel) {
        daoDataSource.storePokemonCaught(pokemonCaught.toEntity())
    }

    override suspend fun freePokemon(id: Int) {
        daoDataSource.setFreePokemonCaught(id)

    }

    override suspend fun freeAllPokemon() {
        daoDataSource.setFreeAllPokemon()
    }

    private fun sortByOrder(pokemonBackpack: List<PokemonModel>): List<PokemonModel> {
        return pokemonBackpack.sortedWith(compareBy { it.order })
    }
}