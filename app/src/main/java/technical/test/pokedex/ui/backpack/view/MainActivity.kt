package technical.test.pokedex.ui.backpack.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint
import technical.test.pokedex.R
import technical.test.pokedex.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackpackView()
        }
    }
}
