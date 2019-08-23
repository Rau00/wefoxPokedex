package technical.test.wefoxpokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import kotlin.coroutines.CoroutineContext

interface BackpackViewModel {

    val pokemonCatched: LiveData<MutableList<PokemonModelView>>
    val backpackEmpty: LiveData<Boolean>
    val errorDataFound: LiveData<String>

    fun initRouter(activity: Activity)
    fun getbackpack(dispatcher: CoroutineContext = Dispatchers.IO)
    fun goHunting()
    fun seePokemonDetail(pokemon: PokemonModelView)
}