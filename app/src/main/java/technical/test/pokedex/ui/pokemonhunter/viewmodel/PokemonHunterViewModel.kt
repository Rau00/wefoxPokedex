package technical.test.pokedex.ui.pokemonhunter.viewmodel

import androidx.lifecycle.LiveData
import technical.test.pokedex.domain.PokemonUserCaseModel

interface PokemonHunterViewModel {

    val pokemonUsercase: LiveData<PokemonUserCaseModel>
    val isCatched: LiveData<Int>

    fun searchPokemon()
    fun catchPokemon()
    fun checkPokemonCatched()
}