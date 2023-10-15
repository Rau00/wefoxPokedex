package technical.test.pokedex.ui.pokemonhunter.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import technical.test.pokedex.CoroutinesTestRule
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.domain.PokemonSprites
import technical.test.pokedex.domain.PokemonType
import technical.test.pokedex.domain.PokemonTypeName
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.network.model.ResultData
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.data.repository.PokemonRepositoyImpl

class PokemonHunterViewModelImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    //Target test
    lateinit var viewModel: PokemonHunterViewModel
    lateinit var viewModelMocked: PokemonHunterViewModel

    //Collaborators
    lateinit var repository: PokemonRepository
    lateinit var repositoryMocked: PokemonRepository
    lateinit var daoDataSource: PokemonLocalDataSource
    lateinit var remoteDataSource: RemoteDataSource

    //Utilities
    lateinit var daoPokemon: PokemonEntity
    lateinit var remotePokemon: PokemonEntity
    lateinit var type: List<PokemonType>

    @Before
    fun setUp() {
        runBlocking {
            daoDataSource = mock()
            remoteDataSource = mock()
            type = listOf(PokemonType(PokemonTypeName("planta")))
            daoPokemon = PokemonEntity(
                1, "bulbasur",
                2, 3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type
            )

            remotePokemon = PokemonEntity(
                1, "bulbasur",
                2, 3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type
            )

            val pokemonList = mutableListOf<PokemonEntity>()
            pokemonList.add(daoPokemon)
            whenever(daoDataSource.getPokemonsCaught()).thenReturn(pokemonList)
            whenever(remoteDataSource.getPokemon(any())).thenReturn(ResultData.Success(remotePokemon))
            repository = PokemonRepositoyImpl(remoteDataSource, daoDataSource)
            viewModel = PokemonHunterViewModelImpl(repository)

            //Mocked classes
            repositoryMocked = mock()
            viewModelMocked = PokemonHunterViewModelImpl(repositoryMocked)
        }
    }

    @Test
    fun `get remote pokemon OK`() {
        viewModel.searchPokemon(Dispatchers.Main)
        assertEquals(remotePokemon.name, viewModel.pokemonFounded.value!!.name)
    }

    @Test
    fun `get remote pokemon call respository OK`() {
        runBlocking {
            viewModelMocked.searchPokemon(Dispatchers.Main)
            verify(repositoryMocked, times(1)).searchPokemon()
        }
    }

    @Test
    fun `catch pokemon pokemon call respository OK`() {
        runBlocking {
            viewModelMocked.catchPokemon(Dispatchers.Main)
            verify(repositoryMocked, times(1)).pokemonCaught()
            verify(repositoryMocked, times(1)).getBackpack()
            verify(repositoryMocked, times(1)).searchPokemon()
        }
    }

    @Test
    fun `check pokemon is catched`() {
        viewModel.catchPokemon(Dispatchers.Main)
        viewModel.checkPokemonCaught()
        assertTrue(viewModel.isCaught.value == View.GONE)
    }

    @Test
    fun `check pokemon is not catched`() {
        runBlocking {
            remotePokemon = PokemonEntity(1, "pikachu", 2,
                3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type)
            whenever(remoteDataSource.getPokemon(any())).thenReturn(ResultData.Success(remotePokemon))
            viewModel.catchPokemon(Dispatchers.Main)
            viewModel.checkPokemonCaught()
            assertTrue(viewModel.isCaught.value == View.VISIBLE)
        }

    }

}