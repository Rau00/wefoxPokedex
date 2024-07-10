package technical.test.pokedex.ui.pokemondetail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import technical.test.pokedex.R
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.utils.constans.Constants

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    lateinit var pokemon: PokemonModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.extras?.let { extras ->
            if (extras.containsKey(Constants.POKEMON_DETAIL)) {
                extras.getParcelable<PokemonModel>(Constants.POKEMON_DETAIL)?.let { it ->
                    pokemon = it
                }
            }
        }
       setContent {
           PokemonDetailView(pokemon = pokemon)
       }
    }
}
