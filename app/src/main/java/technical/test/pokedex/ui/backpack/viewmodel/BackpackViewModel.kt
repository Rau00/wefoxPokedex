package technical.test.pokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import technical.test.pokedex.domain.PokemonUserCaseModel
import technical.test.pokedex.data.models.view.PokemonModelView

interface BackpackViewModel {

    val pokemonUsercase: LiveData<PokemonUserCaseModel>

    fun initRouter(activity: Activity)
    fun getbackpack()
    fun goHunting()
    fun seePokemonDetail(pokemon: PokemonModelView)
}