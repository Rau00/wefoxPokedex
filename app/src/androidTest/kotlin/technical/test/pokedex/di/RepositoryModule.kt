package technical.test.pokedex.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import technical.test.pokedex.data.repository.PokemonRepository
import technical.test.pokedex.repository.stubs.StubPokemonRepositoryImpl
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryTestModule {

    @Binds
    @Singleton
    abstract fun bindFakeRepository(
        pokemonRepositoryImpl: StubPokemonRepositoryImpl
    ): PokemonRepository
}