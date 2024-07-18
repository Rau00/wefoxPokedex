package technical.test.pokedex.ui.backpack

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import technical.test.pokedex.Constants.POKEMON_LIST_ID
import technical.test.pokedex.Constants.POKEMON_LIST_ITEM_ID
import technical.test.pokedex.fakedata.fakePokemonModel
import technical.test.pokedex.ui.MainActivity

@HiltAndroidTest
class BackpackViewTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_backpack_show_pokemon_list() {
        composeTestRule.onNodeWithTag(POKEMON_LIST_ID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(POKEMON_LIST_ITEM_ID).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakePokemonModel.name).assertIsDisplayed()
    }
}