package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.data.repository.PokemonRepositoryImpl

val repositoryModule = module {
    single { PokemonRepositoryImpl(get(), get()) as PokemonRepository }
}