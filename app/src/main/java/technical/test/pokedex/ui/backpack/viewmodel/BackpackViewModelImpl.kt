package technical.test.pokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel
import technical.test.pokedex.data.model.view.PokemonModelView
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.ui.backpack.router.BackpackRouter
import technical.test.pokedex.ui.backpack.router.BackpackRouterImpl
import kotlin.coroutines.CoroutineContext

class BackpackViewModelImpl(private val repository: PokemonRepository) : ViewModel(), BackpackViewModel {

    override val pokemonUsercase: LiveData<PokemonUserCaseModel> = repository.pokemonUsercase

    private lateinit var router: BackpackRouter

    override fun initRouter(activity: Activity) {
        router = BackpackRouterImpl(activity)
    }

    override fun getbackpack() {
        viewModelScope.launch {
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
