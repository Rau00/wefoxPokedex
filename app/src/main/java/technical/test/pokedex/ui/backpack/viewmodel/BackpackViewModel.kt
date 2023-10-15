package technical.test.pokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.backpack.router.BackpackRouter
import technical.test.pokedex.ui.backpack.router.BackpackRouterImpl

class BackpackViewModel(private val getBackpackUseCase: GetBackpackUseCase) : ViewModel() {

    private val _pokemonBackpackResult: MutableStateFlow<PokemonViewStates> = MutableStateFlow(PokemonViewStates.Idle)
    val pokemonBackpackResult = _pokemonBackpackResult.asStateFlow()

    private lateinit var router: BackpackRouter

    fun initRouter(activity: Activity) {
        router = BackpackRouterImpl(activity)
    }

    fun getBackpack() {
        viewModelScope.launch {
            _pokemonBackpackResult.emit(PokemonViewStates.Loading)
            getBackpackUseCase.execute()
                .onSuccess {
                    if (it.isEmpty()) {
                        _pokemonBackpackResult.emit(PokemonViewStates.BackpackEmpty)
                    } else {
                        _pokemonBackpackResult.emit(PokemonViewStates.PokemonCaughtList(it))
                    }
                }
                .onFailure {
                    _pokemonBackpackResult.emit(PokemonViewStates.ErrorDataFound(it.message ?: "ERROR"))
                }
        }
    }

    fun goHunting() {
        router.goHunting()
    }

    fun seePokemonDetail(pokemon: PokemonModel) {
        router.seePokemonDetail(pokemon)
    }
}
