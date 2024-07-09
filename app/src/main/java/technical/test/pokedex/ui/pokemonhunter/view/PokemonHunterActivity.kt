package technical.test.pokedex.ui.pokemonhunter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import technical.test.pokedex.R

@AndroidEntryPoint
class PokemonHunterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContent {
           PokemonHunterView()
       }
    }
}
