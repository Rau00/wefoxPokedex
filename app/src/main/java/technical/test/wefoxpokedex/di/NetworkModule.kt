package technical.test.wefoxpokedex.di

import org.koin.dsl.module
import technical.test.wefoxpokedex.data.network.interfaces.ApiService

val networkModule = module {
    single { ApiService.create() }
}