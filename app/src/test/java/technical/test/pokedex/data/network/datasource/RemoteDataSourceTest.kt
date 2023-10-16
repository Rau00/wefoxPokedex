package technical.test.pokedex.data.network.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import technical.test.pokedex.CoroutinesTestRule
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.RemotePokemonDataSource
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiInterface
import technical.test.pokedex.data.datasources.remote.responses.PokemonResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonSpritesResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonTypeNameResponse
import technical.test.pokedex.data.datasources.remote.responses.PokemonTypeResponse


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
    lateinit var pokemonDefined: PokemonResponse

    @Before
    fun setUp() {
        runTest {
            service = mock(ApiInterface::class.java)
            val type = listOf(PokemonTypeResponse(PokemonTypeNameResponse("planta")))
            pokemonDefined =
                PokemonResponse(0, "pikachu", 7, 40, 12,
                    PokemonSpritesResponse("urlImage"),5, type)
            whenever(service.fetchPokemon(any())).thenReturn(pokemonDefined)
            dataSource = RemotePokemonDataSource(service)
        }
    }

    @Test
    fun `fetch Pokemon response OK`() {
        runTest {
            val pokemon = dataSource.getPokemon(any())
                assertEquals(pokemonDefined.id, pokemon.getOrNull()?.id)
                assertEquals(pokemonDefined.name, pokemon.getOrNull()?.name)
        }
    }

    @Test
    fun `fetch Pokemon response Fail Throwable`() {
        runTest {
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
        runTest {
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

