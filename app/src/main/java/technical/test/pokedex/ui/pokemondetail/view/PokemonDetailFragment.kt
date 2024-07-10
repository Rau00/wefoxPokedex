package technical.test.pokedex.ui.pokemondetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import technical.test.pokedex.R
import technical.test.pokedex.databinding.PokemonDetailFragmentBinding
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.pokemondetail.viewmodel.PokemonDetailViewModel
import technical.test.pokedex.utils.constans.Constants

class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: PokemonDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPokemon()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                setupObservers()
            }
        }
    }

    private fun getPokemon() {
        activity?.intent?.extras?.let { extras ->
            if (extras.containsKey(Constants.POKEMON_DETAIL)) {
                extras.getParcelable<PokemonModel>(Constants.POKEMON_DETAIL)?.let { it ->
                    viewModel.setupPokemonDetail(it)
                }
            }
        }
    }

    private suspend fun setupObservers() {
        viewModel.pokemon.collect {
            it?.let { pokemon ->
                render(pokemon)
            }
        }
    }

    private fun render(pokemon: PokemonModel) {
        binding.apply {
            tvName.text = pokemon.name
            tvWeight.text = resources.getString(R.string.detail_weight) + pokemon.weight
            tvHeight.text = resources.getString(R.string.detail_height) + pokemon.height
            tvCaughtTo.text = resources.getString(R.string.detail_catched) + pokemon.dateCaught
            tvExperience.text =
                resources.getString(R.string.detail_experience) + pokemon.baseExperience
            addTypesView(pokemon.types)
            context?.let { context ->
                Glide.with(context)
                    .load(pokemon.sprite)
                    .into(ivpokemonFounded)
            }
        }
    }

    private fun addTypesView(types: List<String>) {
        binding.apply {
            tvTypes.addView(createTypeTextView(resources.getString(R.string.detail_type)))
            for (type in types) {
                tvTypes.addView(createTypeTextView(type))
            }
        }
    }

    private fun createTypeTextView(value: String): TextView {
        val typeView = TextView(context)
        typeView.text = value
        return typeView
    }
}
