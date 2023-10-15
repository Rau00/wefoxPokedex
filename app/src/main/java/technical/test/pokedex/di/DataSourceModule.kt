package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.RemotePokemonDataSource
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSourceImpl

val dataSourceModule = module {
    single { PokemonLocalDataSourceImpl(get()) as PokemonLocalDataSource }
    single { RemotePokemonDataSource(get()) as RemoteDataSource }
}