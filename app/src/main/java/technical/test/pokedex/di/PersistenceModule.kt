package technical.test.pokedex.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import technical.test.pokedex.data.datasources.local.database.PokemonDDBB
import technical.test.pokedex.data.datasources.local.database.PokemonDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDao(app: Application): PokemonDao =
        PokemonDDBB.getDatabase(app).getPokemonDao()
}