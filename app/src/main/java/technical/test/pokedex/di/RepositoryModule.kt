package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.data.repository.PokemonRepositoyImpl

val repositoryModule = module {
    single { PokemonRepositoyImpl(get(), get()) as PokemonRepository }
}