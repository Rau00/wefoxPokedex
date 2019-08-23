package technical.test.wefoxpokedex.ui.pokemondetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import technical.test.wefoxpokedex.data.model.view.PokemonModelView

class PokemonDetailViewModelImpl : ViewModel(), PokemonDetailViewModel {

    override val pokemon: MutableLiveData<PokemonModelView> = MutableLiveData()

    override fun setupPokemonDetail(pokemon: PokemonModelView) {
        this.pokemon.value = pokemon
    }
}
