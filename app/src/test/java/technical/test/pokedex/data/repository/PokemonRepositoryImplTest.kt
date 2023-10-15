package technical.test.pokedex.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import technical.test.pokedex.data.datasources.remote.network.exceptions.RemoteDataNotFoundException
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.domain.PokemonSprites
import technical.test.pokedex.domain.PokemonType
import technical.test.pokedex.domain.PokemonTypeName
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.network.model.ResultData
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource

class PokemonRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //Target test
    lateinit var repository: PokemonRepository

    //Collaborators
    lateinit var remoteDataSource: RemoteDataSource
    lateinit var daoDataSource: PokemonLocalDataSource

    //Utilities
    lateinit var remotePokemon: PokemonEntity
    lateinit var daoPokemon: PokemonEntity

    @Before
    fun setUp() {
        runBlocking {
            remoteDataSource = mock()
            daoDataSource = mock()
            val typeRemote = listOf(PokemonType(PokemonTypeName("electrico")))
            val typeDao = listOf(PokemonType(PokemonTypeName("planta")))
            remotePokemon = PokemonEntity(0, "pikachu", 7, 40, 12,
                    PokemonSprites("urlImageAPI"), "lunes", 34, typeRemote)
            daoPokemon = PokemonEntity(1, "bulbasur", 2, 3, 4,
                    PokemonSprites("urlImageDAO"), "martes", 123, typeDao)

            whenever(remoteDataSource.getPokemon(any())).thenReturn(ResultData.Success(remotePokemon))
            val pokemonList = mutableListOf<PokemonEntity>()
            pokemonList.add(daoPokemon)
            whenever(daoDataSource.getPokemonsCaught()).thenReturn(pokemonList)

            repository = PokemonRepositoryImpl(remoteDataSource, daoDataSource)
        }
    }

    @Test
    fun `get remote pokemon a convert to model view`(){
            runBlocking {
                launch {
                    repository.searchPokemon()
                    assertEquals(remotePokemon.name, repository.pokemonFounded.value!!.name)
                }
            }
    }

    @Test
    fun `get remote pokemon error not data`(){
        runBlocking {
            val exception = RemoteDataNotFoundException()
            doAnswer { throw exception }.`when`(remoteDataSource).getPokemon(any())
            repository.searchPokemon()
            assertEquals(exception.message, repository.errorDataFound.value)
        }
    }

    @Test
    fun `get the backpack a convert to model view`(){
        runBlocking {
            repository.getBackpack()
            assertEquals(daoPokemon.name, repository.pokemonCaught.value!![0].name)
        }
    }

    @Test
    fun `store pokemon in backpack execution OK`(){
        runBlocking {
            repository.searchPokemon()
            repository.pokemonCaught()
        }
    }

    @Test
    fun `remove all pokemon execution OK`(){
        repository.freeAllPokemon()
    }

    @Test
    fun `remove any pokemon execution OK`(){
        repository.freePokemon(any())
    }
}