package technical.test.pokedex.ui.backpack.router

import android.app.Activity
import android.content.Intent
import technical.test.pokedex.data.model.view.PokemonModelView
import technical.test.pokedex.ui.pokemondetail.view.PokemonDetailActivity
import technical.test.pokedex.ui.pokemonhunter.view.PokemonHunterActivity
import technical.test.pokedex.utils.constans.Constants

class BackpackRouterImpl(val activity: Activity): BackpackRouter {

    override fun goHunting() {
        val intent = Intent(activity, PokemonHunterActivity::class.java)
        activity.startActivity(intent)
    }

    override fun seePokemonDetail(pokemon: PokemonModelView) {
        val intent = Intent(activity, PokemonDetailActivity::class.java)
        intent.putExtra(Constants.POKEMON_DETAIL, pokemon)
        activity.startActivity(intent)
    }
}