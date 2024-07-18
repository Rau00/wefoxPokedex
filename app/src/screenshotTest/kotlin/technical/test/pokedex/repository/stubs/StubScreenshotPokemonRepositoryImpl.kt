package technical.test.pokedex.repository.stubs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.fakedata.fakePokemonModel
import javax.inject.Inject

class StubScreenshotPokemonRepositoryImpl  @Inject constructor(
) : PokemonRepository {

    override suspend fun searchPokemon(pokemonId: Int): Result<PokemonModel> =
       Result.success(fakePokemonModel)


    override suspend fun getBackpack(): Flow<List<PokemonModel>> =
       flow { emit(listOf(fakePokemonModel)) }

    override suspend fun pokemonCaught(pokemonCaught: PokemonModel) {}

    override suspend fun freePokemon(id: Int) {}

    override suspend fun freeAllPokemon() {}

}