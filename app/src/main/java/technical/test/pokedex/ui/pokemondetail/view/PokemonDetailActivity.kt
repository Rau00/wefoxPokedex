package technical.test.pokedex.ui.pokemondetail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import technical.test.pokedex.R

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokemonDetailFragment())
                .commitNow()
        }
    }
}
