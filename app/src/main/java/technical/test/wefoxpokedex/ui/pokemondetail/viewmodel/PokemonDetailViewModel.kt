package technical.test.wefoxpokedex.ui.pokemondetail.viewmodel

import androidx.lifecycle.LiveData
import technical.test.wefoxpokedex.data.model.view.PokemonModelView

interface PokemonDetailViewModel {

    val pokemon: LiveData<PokemonModelView>

    fun setupPokemonDetail(pokemon: PokemonModelView)

}