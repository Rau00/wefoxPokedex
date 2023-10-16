package technical.test.pokedex.ui.backpack.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint
import technical.test.pokedex.R
import technical.test.pokedex.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    BackpackFragment.newInstance()
                )
                .commitNow()
        }
    }
}
