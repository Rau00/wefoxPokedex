package technical.test.wefoxpokedex.data.network.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import retrofit2.HttpException
import technical.test.wefoxpokedex.data.network.interfaces.ApiInterface
import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.model.source.PokemonSprites
import technical.test.wefoxpokedex.data.network.model.ResultData
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.Mockito.`when`
import technical.test.wefoxpokedex.CoroutinesTestRule
import technical.test.wefoxpokedex.data.model.source.PokemonType
import technical.test.wefoxpokedex.data.model.source.PokemonTypeName


class RemoteDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    //Target test
    lateinit var dataSource: RemoteDataSource

    //Collaborators
    lateinit var service: ApiInterface

    //Utilities
    lateinit var pokemonDefined: PokemonModel

    @Before
    fun setUp() {
        runBlocking {
            service = mock()
            val type = listOf(PokemonType(PokemonTypeName("planta")))
            pokemonDefined =
                PokemonModel(0, "pikachu", 7, 40, 12,
                    PokemonSprites("urlImage"), "martes", 5, type)
            whenever(service.fetchPokemon(any())).thenReturn(CompletableDeferred(pokemonDefined))
            dataSource = RemotePokemonDataSource(service)
        }
    }

    @Test
    fun `fetch Pokemon response OK`() {
        runBlocking {
            val pokemon = dataSource.getPokemon(any())
            if (pokemon is ResultData.Success) {
                assertEquals(pokemonDefined.id, pokemon.data.id)
                assertEquals(pokemonDefined.name, pokemon.data.name)
            } else {
                fail()
            }
        }
    }

    @Test
    fun `fetch Pokemon response Fail Throwable`() {
        runBlocking {
            val exception = Throwable()
            doAnswer { throw exception }.`when`(service).fetchPokemon(any())
            try {
                dataSource.getPokemon(any())
            } catch (e: Exception) {
                assert(e is Throwable)
            }
        }
    }

    @Test
    fun `fetch Pokemon response Fail HttpException`() {
        runBlocking {
            val exception: HttpException = mock()
            doAnswer { throw exception }.`when`(service).fetchPokemon(any())
            try {
                dataSource.getPokemon(any())
            } catch (e: Exception) {
                assert(e is HttpException)
            }

        }
    }
}

