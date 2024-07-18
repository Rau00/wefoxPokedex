package technical.test.pokedex.ui.pokemondetail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import technical.test.pokedex.Constants.POKEMON_DETAIL_GENERATION_ID
import technical.test.pokedex.Constants.POKEMON_DETAIL_IMAGE_ID
import technical.test.pokedex.Constants.POKEMON_DETAIL_WIN_PROBABILITY_ID
import technical.test.pokedex.Constants.POKEMON_STATS_ID
import technical.test.pokedex.HiltTestActivity
import technical.test.pokedex.fakedata.fakePokemonModel

@HiltAndroidTest
class PokemonDetailViewTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()


    @Before
    fun setup() {
        composeTestRule.setContent {
            PokemonDetailView(pokemon = fakePokemonModel)
        }
    }

    @Test
    fun testPokemonDetailView() = runTest {
        composeTestRule.onNodeWithTag(POKEMON_DETAIL_IMAGE_ID).assertIsDisplayed()
        composeTestRule.onNodeWithText("${fakePokemonModel.name}(${fakePokemonModel.id})").assertIsDisplayed()
        composeTestRule.onNodeWithTag(POKEMON_DETAIL_WIN_PROBABILITY_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(POKEMON_DETAIL_GENERATION_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(POKEMON_STATS_ID).assertIsDisplayed()
    }

}