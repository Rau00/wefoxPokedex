package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.domain.FreeAllPokemonUseCase
import technical.test.pokedex.domain.FreePokemonUseCase
import technical.test.pokedex.domain.GetBackpackUseCase
import technical.test.pokedex.domain.PokemonCaughtUseCase
import technical.test.pokedex.domain.SearchPokemonUseCase

val useCasesModule = module {
    single { GetBackpackUseCase(get()) }
    single { PokemonCaughtUseCase(get()) }
    single { SearchPokemonUseCase(get()) }
    single { FreePokemonUseCase(get()) }
    single { FreeAllPokemonUseCase(get()) }
}