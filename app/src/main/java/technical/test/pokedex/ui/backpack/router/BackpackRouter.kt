package technical.test.pokedex.ui.backpack.router

import android.app.Activity
import technical.test.pokedex.domain.models.PokemonModel

interface BackpackRouter {

    fun goHunting(activity: Activity)
    fun seePokemonDetail(activity: Activity, pokemon: PokemonModel)
}