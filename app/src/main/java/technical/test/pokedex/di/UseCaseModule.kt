package technical.test.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.domain.FreeAllPokemonUseCase
import technical.test.pokedex.domain.FreePokemonUseCase
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.PokemonCaughtUseCase
import technical.test.pokedex.domain.SearchPokemonUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetBackpackUseCase(repository: PokemonRepository): GetBackpackUseCase =
        GetBackpackUseCase(repository)

    @Provides
    @Singleton
    fun providePokemonCaughtUseCase(repository: PokemonRepository): PokemonCaughtUseCase =
        PokemonCaughtUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchPokemonUseCase(repository: PokemonRepository): SearchPokemonUseCase =
        SearchPokemonUseCase(repository)


    @Provides
    @Singleton
    fun provideFreePokemonUseCase(repository: PokemonRepository): FreePokemonUseCase =
        FreePokemonUseCase(repository)


    @Provides
    @Singleton
    fun provideFreeAllPokemonUseCase(repository: PokemonRepository): FreeAllPokemonUseCase =
        FreeAllPokemonUseCase(repository)
}