package technical.test.pokedex.ui.pokemondetail.viewmodel

import androidx.lifecycle.LiveData
import technical.test.pokedex.data.model.view.PokemonModelView

interface PokemonDetailViewModel {

    val pokemon: LiveData<PokemonModelView>

    fun setupPokemonDetail(pokemon: PokemonModelView)

}