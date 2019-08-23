package technical.test.wefoxpokedex.ui.backpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import technical.test.wefoxpokedex.CoroutinesTestRule
import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.model.source.PokemonSprites
import technical.test.wefoxpokedex.data.model.source.PokemonType
import technical.test.wefoxpokedex.data.model.source.PokemonTypeName
import technical.test.wefoxpokedex.data.network.datasource.RemoteDataSource
import technical.test.wefoxpokedex.data.persistence.datasource.PersistenceDataSource
import technical.test.wefoxpokedex.data.repository.PokemonRepository
import technical.test.wefoxpokedex.data.repository.PokemonRepositoyImpl

class BackpackViewModelImplTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    //Target test
    lateinit var viewModel: BackpackViewModel

    //Collaborators
    lateinit var repository: PokemonRepository
    lateinit var daoDataSource: PersistenceDataSource


    //Utilities
    lateinit var daoPokemon: PokemonModel

    @Before
    fun setUp() {
        runBlocking {
            daoDataSource = mock()
            val type = listOf(PokemonType(PokemonTypeName("planta")))
            daoPokemon = PokemonModel(
                1, "bulbasur",
                2, 3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type
            )
            val pokemonList = mutableListOf<PokemonModel>()
            pokemonList.add(daoPokemon)
            whenever(daoDataSource.getPokemonsCatched()).thenReturn(pokemonList)
            repository = PokemonRepositoyImpl(mock(), daoDataSource)
            viewModel = BackpackViewModelImpl(repository)
        }
    }

    @Test
    fun `get Backpack OK`() {
        viewModel.getbackpack(Dispatchers.Main)
        assertEquals(daoPokemon.name, viewModel.pokemonCatched.value!![0].name)
    }
}