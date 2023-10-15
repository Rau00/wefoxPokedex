package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.datasources.local.database.PokemonDDBB

val persistenceModule = module {
    single { PokemonDDBB.getDatabase(get()).getPokemonDao() }
}