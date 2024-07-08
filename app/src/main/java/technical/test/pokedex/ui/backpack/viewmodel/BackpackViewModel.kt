package technical.test.pokedex.ui.backpack.viewmodel

import android.app.Activity
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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
class BackpackViewModel @Inject constructor(private val getBackpackUseCase: GetBackpackUseCase) :
    ViewModel() {

    private val _pokemonBackpackResult: MutableStateFlow<PokemonViewStates> =
        MutableStateFlow(PokemonViewStates.Idle)
    val pokemonBackpackResult = _pokemonBackpackResult.asStateFlow()

    fun updateBackpack() {
        if (pokemonBackpackResult.value != PokemonViewStates.Idle) {
            getBackpack()
        }
    }

    fun getBackpack() {
        viewModelScope.launch {
            _pokemonBackpackResult.emit(PokemonViewStates.Loading)
            getBackpackUseCase()
                .collect {
                    val list: SnapshotStateList<PokemonModel> = mutableStateListOf()
                    list.addAll(it.shuffled())
                    if (it.isEmpty()) {
                        _pokemonBackpackResult.emit(PokemonViewStates.BackpackEmpty)
                    } else {
                        _pokemonBackpackResult.emit(PokemonViewStates.PokemonCaughtList(list))
                    }
                }
        }
    }

    fun goHunting(activity: Activity) {
        BackpackRouterImpl.goHunting(activity)
    }

    fun seePokemonDetail(activity: Activity, pokemon: PokemonModel) {
        BackpackRouterImpl.seePokemonDetail(activity, pokemon)
    }

    fun sortAlphabetical() {
        if (_pokemonBackpackResult.value is PokemonViewStates.PokemonCaughtList) {
            val list: SnapshotStateList<PokemonModel> =
                (_pokemonBackpackResult.value as PokemonViewStates.PokemonCaughtList).pokemonCaughtList
            _pokemonBackpackResult.value = PokemonViewStates.Loading

            viewModelScope.launch {
                val result = quicksort(list)
                // (_pokemonBackpackResult.value as PokemonCaughtList).pokemonCaughtList.sortedWith(
                //      compareBy(PokemonModel::order).reversed() )

                _pokemonBackpackResult.emit(PokemonViewStates.PokemonCaughtList(result))
            }
        }
    }

    private fun quicksort(
        list: SnapshotStateList<PokemonModel>,
        left: Int = 0,
        right: Int = list.size - 1
    ): SnapshotStateList<PokemonModel> {
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

        if (left < end) {
            quicksort(list, left, end)
        }

        if (start < right) {
            quicksort(list, start, right)
        }

        return list
    }
}
