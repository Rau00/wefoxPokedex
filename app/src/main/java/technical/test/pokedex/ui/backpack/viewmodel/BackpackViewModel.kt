package technical.test.pokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel
import technical.test.pokedex.data.model.view.PokemonModelView
import kotlin.coroutines.CoroutineContext

interface BackpackViewModel {

    val pokemonUsercase: LiveData<PokemonUserCaseModel>

    fun initRouter(activity: Activity)
    fun getbackpack()
    fun goHunting()
    fun seePokemonDetail(pokemon: PokemonModelView)
}