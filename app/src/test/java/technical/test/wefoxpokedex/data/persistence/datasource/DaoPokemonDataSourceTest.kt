package technical.test.wefoxpokedex.data.persistence.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.model.source.PokemonSprites
import technical.test.wefoxpokedex.data.model.source.PokemonType
import technical.test.wefoxpokedex.data.model.source.PokemonTypeName
import technical.test.wefoxpokedex.data.persistence.database.PokemonDao

class DaoPokemonDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //Target test
    lateinit var dataSource: PersistenceDataSource

    //Collaborators
    lateinit var dao: PokemonDao

    //Utilities
    lateinit var daoPokemon: PokemonModel

    @Before
    fun setUp() {
        runBlocking {
            dao = mock()
            val type = listOf(PokemonType(PokemonTypeName("planta")))
            daoPokemon =
                PokemonModel(0, "pikachu", 7, 40, 12,
                    PokemonSprites("urlImage"), "lunes", 4, type)
            val pokemonList = mutableListOf<PokemonModel>()
            pokemonList.add(daoPokemon)
            whenever(dao.getAllPokemon()).thenReturn(pokemonList)
            dataSource = PersistencePokemonDataSource(dao)
        }
    }

    @Test
    fun `get all pokemon in dao execute ok`() {
        runBlocking {
            val pokemonList = dataSource.getPokemonsCatched()
            assertEquals(daoPokemon.name, pokemonList[0].name)
        }
    }

    @Test
    fun `insert pokemon in dao execute ok`() {
        runBlocking {
            dataSource.storePokemonCatched(daoPokemon)
        }
    }

    @Test
    fun `removed all pokemon in dao execute ok`() {
        runBlocking {
            dataSource.setFreeAllPokemon()
        }
    }

    @Test
    fun `removed one pokemon in dao execute ok`() {
        runBlocking {
            dataSource.setFreePokemonCatched(any())
        }
    }

}