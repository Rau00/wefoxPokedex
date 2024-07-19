package technical.test.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import technical.test.pokedex.fakedata.fakePokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.pokemonhunter.PokemonHunterView

class PokemonHunterPreviewsScreenshots {

    @Preview(showBackground = true)
    @Composable
    fun PokemonHunterPreview_Pokemon_Found_State() {
        val pokemonStateFlow = MutableStateFlow(PokemonViewStates.PokemonFounded(fakePokemonModel)).asStateFlow()
        PokemonHunterView(
            viewState = pokemonStateFlow,
            handleIntent = {},
            leaveAction = {}
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PokemonHunterPreview_Error_State() {
        val pokemonStateFlow = MutableStateFlow(PokemonViewStates.ErrorDataFound("Error")).asStateFlow()
        PokemonHunterView(
            viewState = pokemonStateFlow,
            handleIntent = {},
            leaveAction = {}
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PokemonHunterPreview_Loading_State() {
        val pokemonStateFlow = MutableStateFlow(PokemonViewStates.Loading).asStateFlow()
        PokemonHunterView(
            viewState = pokemonStateFlow,
            handleIntent = {},
            leaveAction = {}
        )
    }
}