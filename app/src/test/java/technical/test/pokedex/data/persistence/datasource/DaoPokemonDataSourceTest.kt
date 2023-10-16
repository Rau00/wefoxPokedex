package technical.test.pokedex.data.persistence.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSourceImpl
import technical.test.pokedex.data.datasources.local.database.PokemonDao
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity

class DaoPokemonDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //Target test
    lateinit var dataSource: PokemonLocalDataSource

    //Collaborators
    lateinit var dao: PokemonDao

    //Utilities
    lateinit var daoPokemon: PokemonEntity

    @Before
    fun setUp() {
        runTest {
            dao = mock()
            val type = listOf("planta")
            daoPokemon =
                PokemonEntity(0, "pikachu", 7, 40, 12,
                    "urlImage", "lunes", 4, type)
            val pokemonList = mutableListOf<PokemonEntity>()
            pokemonList.add(daoPokemon)
            whenever(dao.getAllPokemon()).thenReturn(pokemonList)
            dataSource = PokemonLocalDataSourceImpl(dao)
        }
    }

    @Test
    fun `get all pokemon in dao execute ok`() {
        runTest {
            val pokemonListResult = dataSource.getPokemonsCaught()
            assertEquals(daoPokemon.name, pokemonListResult.getOrNull()?.get(0)?.name)
        }
    }

    @Test
    fun `insert pokemon in dao execute ok`() {
        runTest {
            dataSource.storePokemonCaught(daoPokemon)
        }
    }

    @Test
    fun `removed all pokemon in dao execute ok`() {
        runTest {
            dataSource.setFreeAllPokemon()
        }
    }

    @Test
    fun `removed one pokemon in dao execute ok`() {
        runTest {
            dataSource.setFreePokemonCaught(any())
        }
    }

}