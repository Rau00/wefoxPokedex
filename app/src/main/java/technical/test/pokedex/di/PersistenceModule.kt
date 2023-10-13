package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.persistence.database.PokemonDataBase

val persistenceModule = module {
    single { PokemonDataBase.getDatabase(get()).getPokemonDao() }
}