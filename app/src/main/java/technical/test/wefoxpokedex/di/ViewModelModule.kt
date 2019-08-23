package technical.test.wefoxpokedex.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import technical.test.wefoxpokedex.ui.backpack.viewmodel.BackpackViewModelImpl
import technical.test.wefoxpokedex.ui.pokemondetail.viewmodel.PokemonDetailViewModelImpl
import technical.test.wefoxpokedex.ui.pokemonhunter.viewmodel.PokemonHunterViewModelImpl

val viewModelModule = module {
    viewModel { BackpackViewModelImpl(get()) }
    viewModel { PokemonHunterViewModelImpl(get()) }
    viewModel { PokemonDetailViewModelImpl() }
}