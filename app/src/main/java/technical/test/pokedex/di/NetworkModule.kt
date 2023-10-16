package technical.test.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiInterface
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): ApiInterface =
        ApiService.create()
}