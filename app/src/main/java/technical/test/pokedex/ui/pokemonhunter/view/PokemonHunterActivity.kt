package technical.test.pokedex.ui.pokemonhunter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import technical.test.pokedex.R

@AndroidEntryPoint
class PokemonHunterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_hunter_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    PokemonHunterFragment.newInstance()
                )
                .commitNow()
        }
    }
}
