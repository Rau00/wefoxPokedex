package technical.test.wefoxpokedex.di

import org.koin.dsl.module
import technical.test.wefoxpokedex.data.network.datasource.RemoteDataSource
import technical.test.wefoxpokedex.data.network.datasource.RemotePokemonDataSource
import technical.test.wefoxpokedex.data.persistence.datasource.PersistenceDataSource
import technical.test.wefoxpokedex.data.persistence.datasource.PersistencePokemonDataSource

val dataSourceModule = module {
    single { PersistencePokemonDataSource(get()) as PersistenceDataSource }
    single { RemotePokemonDataSource(get()) as RemoteDataSource }
}