package technical.test.pokedex.ui.pokemonhunter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import technical.test.pokedex.CoroutinesTestRule
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.PokemonCaughtUseCase
import technical.test.pokedex.domain.SearchPokemonUseCase
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.pokemonhunter.PokemonHunterViewModel

class PokemonHunterViewModelImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    //Collaborators
    private val getBackpackUseCase: GetBackpackUseCase = mock()
    private val searchPokemonUseCase: SearchPokemonUseCase = mock()
    private val pokemonCaughtUseCase: PokemonCaughtUseCase = mock()

    //Target test
    private var viewModel =
        PokemonHunterViewModel(getBackpackUseCase, searchPokemonUseCase, pokemonCaughtUseCase)

    //Utilities
    private lateinit var pokemonModel: PokemonModel

    @Before
    fun setUp() {
        runTest {

            pokemonModel = PokemonModel(
                1, "bulbasur",
                2, 3, 4, "urlImageDAO",
                "lunes", 5, listOf("planta"), listOf()
            )

            val pokemonList = mutableListOf<PokemonModel>()
            pokemonList.add(pokemonModel)
            whenever(getBackpackUseCase()).thenReturn(MutableStateFlow(pokemonList))
            whenever(searchPokemonUseCase()).thenReturn(Result.success(pokemonModel))
        }
    }

    @Test
    fun `get remote pokemon OK`() {
        viewModel.searchPokemon()
        assertEquals(PokemonViewStates.PokemonFounded(pokemonModel), viewModel.pokemonFound.value)
    }

    @Test
    fun `get remote pokemon call repository OK`() {
        runTest {
            viewModel.searchPokemon()
            verify(searchPokemonUseCase, times(1)).invoke()
        }
    }

    @Test
    fun `catch pokemon pokemon call repository OK`() {
        runTest {
            viewModel.searchPokemon()
            viewModel.catchPokemon()
            verify(pokemonCaughtUseCase, times(1)).invoke(any())
            verify(searchPokemonUseCase, times(2)).invoke()
        }
    }

    @Test
    fun `check pokemon is caught`() {
        viewModel.searchPokemon()
        viewModel.catchPokemon()
        assertTrue(viewModel.isCaught)
    }
}