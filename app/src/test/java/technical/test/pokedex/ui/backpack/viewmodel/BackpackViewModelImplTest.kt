package technical.test.pokedex.ui.backpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
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
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.data.repository.PokemonRepositoyImpl

class BackpackViewModelImplTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    //Target test
    lateinit var viewModel: BackpackViewModel

    //Collaborators
    lateinit var repository: PokemonRepository
    lateinit var daoDataSource: PokemonLocalDataSource


    //Utilities
    lateinit var daoPokemon: PokemonEntity

    @Before
    fun setUp() {
        runBlocking {
            daoDataSource = mock()
            val type = listOf(PokemonType(PokemonTypeName("planta")))
            daoPokemon = PokemonEntity(
                1, "bulbasur",
                2, 3, 4, PokemonSprites("urlImageDAO"),
                "lunes", 5, type
            )
            val pokemonList = mutableListOf<PokemonEntity>()
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