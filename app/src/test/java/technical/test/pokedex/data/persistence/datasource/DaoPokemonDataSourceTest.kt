package technical.test.pokedex.data.persistence.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSourceImpl
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.domain.PokemonSprites
import technical.test.pokedex.domain.PokemonType
import technical.test.pokedex.domain.PokemonTypeName
import technical.test.pokedex.data.datasources.local.database.PokemonDao

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
        runBlocking {
            dao = mock()
            val type = listOf(PokemonType(PokemonTypeName("planta")))
            daoPokemon =
                PokemonEntity(0, "pikachu", 7, 40, 12,
                    PokemonSprites("urlImage"), "lunes", 4, type)
            val pokemonList = mutableListOf<PokemonEntity>()
            pokemonList.add(daoPokemon)
            whenever(dao.getAllPokemon()).thenReturn(pokemonList)
            dataSource = PokemonLocalDataSourceImpl(dao)
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