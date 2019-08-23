package technical.test.wefoxpokedex.ui.pokemonhunter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import technical.test.wefoxpokedex.R

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
