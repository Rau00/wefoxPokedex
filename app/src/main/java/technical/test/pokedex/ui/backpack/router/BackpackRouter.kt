package technical.test.pokedex.ui.backpack.router

import technical.test.pokedex.domain.models.PokemonModel

interface BackpackRouter {

    fun goHunting()
    fun seePokemonDetail(pokemon: PokemonModel)
}