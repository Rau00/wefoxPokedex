package technical.test.pokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.backpack.router.BackpackRouter
import technical.test.pokedex.ui.backpack.router.BackpackRouterImpl
import javax.inject.Inject

@HiltViewModel
class BackpackViewModel @Inject constructor(private val getBackpackUseCase: GetBackpackUseCase) : ViewModel() {

    private val _pokemonBackpackResult: MutableStateFlow<PokemonViewStates> =
        MutableStateFlow(PokemonViewStates.Idle)
    val pokemonBackpackResult = _pokemonBackpackResult.asStateFlow()

    private lateinit var router: BackpackRouter

    fun initRouter(activity: Activity) {
        router = BackpackRouterImpl(activity)
    }

    fun updateBackpack() {
        if (pokemonBackpackResult.value != PokemonViewStates.Idle) {
            getBackpack()
        }
    }

    fun getBackpack() {
        viewModelScope.launch {
            _pokemonBackpackResult.emit(PokemonViewStates.Loading)
            getBackpackUseCase()
                .onSuccess {
                    if (it.isEmpty()) {
                        _pokemonBackpackResult.emit(PokemonViewStates.BackpackEmpty)
                    } else {
                        _pokemonBackpackResult.emit(PokemonViewStates.PokemonCaughtList(it.shuffled()))
                    }
                }
                .onFailure {
                    _pokemonBackpackResult.emit(
                        PokemonViewStates.ErrorDataFound(
                            it.message ?: "ERROR"
                        )
                    )
                }
        }
    }

    fun goHunting() {
        router.goHunting()
    }

    fun seePokemonDetail(pokemon: PokemonModel) {
        router.seePokemonDetail(pokemon)
    }

    fun sortAlphabetical() {



        if (_pokemonBackpackResult.value is PokemonViewStates.PokemonCaughtList) {
            val list: List<PokemonModel> = (_pokemonBackpackResult.value as PokemonViewStates.PokemonCaughtList).pokemonCaughtList
            _pokemonBackpackResult.value = PokemonViewStates.Loading

            viewModelScope.launch {
            val result = quicksort(list as MutableList<PokemonModel>)
               // (_pokemonBackpackResult.value as PokemonCaughtList).pokemonCaughtList.sortedWith(
              //      compareBy(PokemonModel::order).reversed() )

                _pokemonBackpackResult.emit(PokemonViewStates.PokemonCaughtList(result))
            }
        }
    }

    private fun quicksort(
        list: MutableList<PokemonModel>,
        left: Int = 0,
        right: Int = list.size - 1
    ): List<PokemonModel> {
        var start = left
        var end = right
        val pivot = list[(left + right) / 2].order

        while (start <= end) {
            while (list[start].order < pivot) {
                start++
            }

            while (list[end].order > pivot) {
                end--
            }
            if (start <= end) {
                val temp = list[start]
                list[start] = list[end]
                list[end] = temp
                start++
                end--
            }
        }

        if(left < end) {
            quicksort(list, left, end)
        }

        if(start < right) {
            quicksort(list, start, right)
        }

        return list
    }
}
