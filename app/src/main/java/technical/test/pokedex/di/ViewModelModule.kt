package technical.test.pokedex.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import technical.test.pokedex.ui.backpack.viewmodel.BackpackViewModelImpl
import technical.test.pokedex.ui.pokemondetail.viewmodel.PokemonDetailViewModelImpl
import technical.test.pokedex.ui.pokemonhunter.viewmodel.PokemonHunterViewModelImpl

val viewModelModule = module {
    viewModel { BackpackViewModelImpl(get()) }
    viewModel { PokemonHunterViewModelImpl(get()) }
    viewModel { PokemonDetailViewModelImpl() }
}