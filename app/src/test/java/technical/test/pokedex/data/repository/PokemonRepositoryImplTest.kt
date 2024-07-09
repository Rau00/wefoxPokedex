package technical.test.pokedex.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.network.exceptions.RemoteDataNotFoundException
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonSpritesResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonTypeNameResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonTypeResponse
import technical.test.pokedex.domain.models.mapper.toModel

class PokemonRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //Target test
    lateinit var repository: PokemonRepository

    //Collaborators
    lateinit var remoteDataSource: RemoteDataSource
    lateinit var daoDataSource: PokemonLocalDataSource

    //Utilities
    lateinit var remotePokemon: PokemonResponse
    lateinit var daoPokemon: PokemonEntity

    @Before
    fun setUp() {
        runTest {
            remoteDataSource = mock()
            daoDataSource = mock()
            val typeRemote = listOf(PokemonTypeResponse(PokemonTypeNameResponse("electrico")))
            val typeDao = listOf("planta")
            remotePokemon = PokemonResponse(0, "pikachu", 7, 40, 12,
                PokemonSpritesResponse("urlImageAPI"), 34, typeRemote)
            daoPokemon = PokemonEntity(1, "bulbasur", 2, 3, 4,
                "urlImageDAO", "martes", 123, typeDao)

            whenever(remoteDataSource.getPokemon(any())).thenReturn(Result.success(remotePokemon))
            val pokemonList = mutableListOf<PokemonEntity>()
            pokemonList.add(daoPokemon)
            whenever(daoDataSource.getPokemonsCaught()).thenReturn(MutableStateFlow(pokemonList))

            repository = PokemonRepositoryImpl(remoteDataSource, daoDataSource)
        }
    }

    @Test
    fun `get remote pokemon a convert to model view`(){
            runTest {
                launch {
                    val actual = repository.searchPokemon(0)
                    assertEquals(remotePokemon.name, actual.getOrNull()?.name)
                }
            }
    }

    @Test
    fun `get remote pokemon error not data`(){
        runTest {
            val exception = RemoteDataNotFoundException()
            whenever( remoteDataSource.getPokemon(any()) ).thenReturn( Result.failure(exception) )
            val actual = repository.searchPokemon(0)
            assertEquals(exception, actual.exceptionOrNull())
        }
    }

    @Test
    fun `get the backpack a convert to model view`(){
        runTest {
            val actual =  repository.getBackpack()
            assertEquals(daoPokemon.name, actual.firstOrNull()?.get(0)?.name)
        }
    }

    @Test
    fun `store pokemon in backpack execution OK`(){
        runTest {
            repository.pokemonCaught(remotePokemon.toModel())
        }
    }

    @Test
    fun `remove all pokemon execution OK`(){
        runTest {
            repository.freeAllPokemon()
        }
    }

    @Test
    fun `remove any pokemon execution OK`(){
        runTest {
            repository.freePokemon(any())
        }
    }
}