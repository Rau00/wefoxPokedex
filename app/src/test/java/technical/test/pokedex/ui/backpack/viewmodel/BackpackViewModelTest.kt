package technical.test.pokedex.ui.backpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import technical.test.pokedex.CoroutinesTestRule
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates

class BackpackViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    //Target test
    lateinit var viewModel: BackpackViewModel

    //Collaborators
    var getBackpackUseCase: GetBackpackUseCase = mock()


    //Utilities
    private lateinit var pokemonModel: PokemonModel

    @Before
    fun setUp() {
        runTest {
            val type = listOf("planta")
            pokemonModel = PokemonModel(
                1, "bulbasur",
                2, 3, 4, "urlImageDAO",
                "lunes", 5, type
            )
            val pokemonList = mutableListOf<PokemonModel>()
            pokemonList.add(pokemonModel)
            whenever(getBackpackUseCase()).thenReturn(Result.success(pokemonList))
            viewModel = BackpackViewModel(getBackpackUseCase)
        }
    }

    @Test
    fun `get Backpack OK`() {
        runTest {
            viewModel.getBackpack()
            assertEquals(PokemonViewStates.PokemonCaughtList(any()), viewModel.pokemonBackpackResult.value)
        }
    }
}