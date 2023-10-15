package technical.test.pokedex.ui.backpack.router

import technical.test.pokedex.data.models.view.PokemonModelView

interface BackpackRouter {

    fun goHunting()
    fun seePokemonDetail(pokemon: PokemonModelView)
}