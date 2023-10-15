package technical.test.pokedex.di

import org.koin.dsl.module
import technical.test.pokedex.data.datasources.remote.network.interfaces.ApiService

val networkModule = module {
    single { ApiService.create() }
}