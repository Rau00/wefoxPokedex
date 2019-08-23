package technical.test.wefoxpokedex.ui.backpack.router

import technical.test.wefoxpokedex.data.model.view.PokemonModelView

interface BackpackRouter {

    fun goHunting()
    fun seePokemonDetail(pokemon: PokemonModelView)
}