package technical.test.wefoxpokedex.di

import org.koin.dsl.module
import technical.test.wefoxpokedex.data.repository.PokemonRepository
import technical.test.wefoxpokedex.data.repository.PokemonRepositoyImpl

val repositoryModule = module {
    single { PokemonRepositoyImpl(get(), get()) as PokemonRepository }
}