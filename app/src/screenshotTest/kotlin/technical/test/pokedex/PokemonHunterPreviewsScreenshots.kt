package technical.test.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import technical.test.pokedex.fakedata.fakePokemonModel
import technical.test.pokedex.ui.pokemonhunter.PokemonFounded

class PokemonHunterPreviewsScreenshots {
    @Preview(showBackground = true)
    @Composable
    fun PokemonHunterPreview() {
        PokemonFounded(pokemon = fakePokemonModel,
            pokemonCaught = false,
            catchAction = {},
            leaveAction = {},
            searchAction = {})
    }
}