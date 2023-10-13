package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.network.datasource.RemoteDataSource
import technical.test.pokedex.data.network.datasource.RemotePokemonDataSource
import technical.test.pokedex.data.persistence.datasource.PersistenceDataSource
import technical.test.pokedex.data.persistence.datasource.PersistencePokemonDataSource

val dataSourceModule = module {
    single { PersistencePokemonDataSource(get()) as PersistenceDataSource }
    single { RemotePokemonDataSource(get()) as RemoteDataSource }
}