package technical.test.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSourceImpl
import technical.test.pokedex.data.datasources.local.database.PokemonDao
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.RemotePokemonDataSource
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiInterface
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ApiInterface): RemoteDataSource =
        RemotePokemonDataSource(api)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: PokemonDao): PokemonLocalDataSource =
        PokemonLocalDataSourceImpl(dao)

}