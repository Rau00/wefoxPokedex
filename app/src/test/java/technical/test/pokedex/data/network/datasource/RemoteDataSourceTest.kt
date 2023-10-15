package technical.test.pokedex.data.network.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import technical.test.pokedex.CoroutinesTestRule
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.RemotePokemonDataSource
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiInterface
import technical.test.pokedex.data.datasources.remote.network.model.ResultData
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
        runBlocking {
            service = mock()
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

