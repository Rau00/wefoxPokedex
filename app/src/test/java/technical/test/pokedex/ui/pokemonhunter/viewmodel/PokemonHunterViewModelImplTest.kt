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
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.source.PokemonSprites
import technical.test.pokedex.data.model.source.PokemonType
import technical.test.pokedex.data.model.source.PokemonTypeName
import technical.test.pokedex.data.network.datasource.RemoteDataSource
import technical.test.pokedex.data.network.model.ResultData
import technical.test.pokedex.data.persistence.datasource.PersistenceDataSource
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
    lateinit var daoDataSource: PersistenceDataSource
    lateinit var remoteDataSource: RemoteDataSource

    //Utilities
    lateinit var daoPokemon: PokemonModel
    lateinit var remotePokemon: PokemonModel
    lateinit var type: List<PokemonType>

    @Before
    fun setUp() {
        runBlocking {
            daoDataSource = mock()
            remoteDataSource = mock()
            type = listOf(PokemonType(PokemonTypeName("planta")))
            daoPokemon = PokemonModel(
                1, "bulbasur",
                2, 3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type
            )

            remotePokemon = PokemonModel(
                1, "bulbasur",
                2, 3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type
            )

            val pokemonList = mutableListOf<PokemonModel>()
            pokemonList.add(daoPokemon)
            whenever(daoDataSource.getPokemonsCatched()).thenReturn(pokemonList)
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
            verify(repositoryMocked, times(1)).pokemonCatched()
            verify(repositoryMocked, times(1)).getBackpack()
            verify(repositoryMocked, times(1)).searchPokemon()
        }
    }

    @Test
    fun `check pokemon is catched`() {
        viewModel.catchPokemon(Dispatchers.Main)
        viewModel.checkPokemonCatched()
        assertTrue(viewModel.isCatched.value == View.GONE)
    }

    @Test
    fun `check pokemon is not catched`() {
        runBlocking {
            remotePokemon = PokemonModel(1, "pikachu", 2,
                3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type)
            whenever(remoteDataSource.getPokemon(any())).thenReturn(ResultData.Success(remotePokemon))
            viewModel.catchPokemon(Dispatchers.Main)
            viewModel.checkPokemonCatched()
            assertTrue(viewModel.isCatched.value == View.VISIBLE)
        }

    }

}