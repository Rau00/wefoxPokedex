package technical.test.wefoxpokedex.di

import org.koin.dsl.module
import technical.test.wefoxpokedex.data.persistence.database.PokemonDataBase

val persistenceModule = module {
    single { PokemonDataBase.getDatabase(get()).getPokemonDao() }
}