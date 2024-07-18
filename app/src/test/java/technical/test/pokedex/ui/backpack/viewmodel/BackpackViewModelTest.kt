package technical.test.pokedex.ui.backpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import technical.test.pokedex.CoroutinesTestRule
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.backpack.BackpackViewModel

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
    private val type = listOf("planta")
    private var pokemonModel = PokemonModel(
    1, "bulbasur",
    2, 3, 4, "urlImageDAO",
    "lunes", 5, type, listOf()
    )
    private val pokemonList = SnapshotStateList<PokemonModel>()

    @Before
    fun setUp() {
        runTest {
            pokemonList.add(pokemonModel)
            whenever(getBackpackUseCase()).thenReturn(MutableStateFlow(pokemonList))
            viewModel = BackpackViewModel(getBackpackUseCase)
        }
    }

    @Test
    fun `get Backpack OK`() {
        runTest {
            viewModel.getBackpack()
            (getBackpackUseCase() as MutableStateFlow).emit(pokemonList)
            assertEquals(
                PokemonViewStates.PokemonCaughtList(pokemonList).pokemonCaughtList[0].name,
                (viewModel.pokemonBackpackResult.value as PokemonViewStates.PokemonCaughtList).pokemonCaughtList[0].name
            )
        }
    }
}