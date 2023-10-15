package technical.test.pokedex.ui.pokemondetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import technical.test.pokedex.data.models.view.PokemonModelView

class PokemonDetailViewModelImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //Target test
    lateinit var viewModel: PokemonDetailViewModel

    //Utilities
    lateinit var pokemon: PokemonModelView

    @Before
    fun setUp() {

        pokemon = PokemonModelView(
            "bulbasur", "2", "3",
            "urlImageDAO", "lunes", "5", listOf("planta"))
        viewModel = PokemonDetailViewModelImpl()
    }

    @Test
    fun `set pokemon OK`() {
        viewModel.setupPokemonDetail(pokemon)
        assertEquals(pokemon.name, viewModel.pokemon.value!!.name)

    }
}