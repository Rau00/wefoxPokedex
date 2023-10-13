package technical.test.pokedex

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import technical.test.pokedex.di.*

class PokedexApp: Application() {

    companion object {
        lateinit var instance: PokedexApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            // declare used Android context
            androidContext(this@PokedexApp)
            // declare modules
            modules(listOf(
                dataSourceModule, persistenceModule, repositoryModule,
                viewModelModule, networkModule))
        }
    }
}