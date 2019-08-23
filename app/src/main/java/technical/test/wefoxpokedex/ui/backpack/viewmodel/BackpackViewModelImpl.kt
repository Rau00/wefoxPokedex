package technical.test.wefoxpokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import technical.test.wefoxpokedex.data.repository.PokemonRepository
import technical.test.wefoxpokedex.ui.backpack.router.BackpackRouter
import technical.test.wefoxpokedex.ui.backpack.router.BackpackRouterImpl
import kotlin.coroutines.CoroutineContext

class BackpackViewModelImpl(private val repository: PokemonRepository) : ViewModel(), BackpackViewModel {

    override val pokemonCatched: LiveData<MutableList<PokemonModelView>> = repository.pokemonCatched
    override val backpackEmpty: LiveData<Boolean> = repository.backpackEmpty
    override val errorDataFound: LiveData<String> = repository.errorDataFound

    private lateinit var router: BackpackRouter

    override fun initRouter(activity: Activity) {
        router = BackpackRouterImpl(activity)
    }

    override fun getbackpack(dispatcher: CoroutineContext) {
        viewModelScope.launch(dispatcher) {
            repository.getBackpack()
        }
    }

    override fun goHunting() {
        router.goHunting()
    }

    override fun seePokemonDetail(pokemon: PokemonModelView) {
        router.seePokemonDetail(pokemon)
    }
}
