package technical.test.pokedex.ui.pokemondetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.getViewModel
import technical.test.pokedex.R
import technical.test.pokedex.data.model.view.PokemonModelView
import technical.test.pokedex.databinding.PokemonDetailFragmentBinding
import technical.test.pokedex.ui.pokemondetail.viewmodel.PokemonDetailViewModelImpl
import technical.test.pokedex.utils.constans.Constants

class PokemonDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    private lateinit var viewModel: PokemonDetailViewModelImpl
    private lateinit var binding: PokemonDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        getPokemon()
        setuoObservers()
    }

    private fun getPokemon() {
        activity?.intent?.extras?.let {extras ->
            if (extras.containsKey(Constants.POKEMON_DETAIL)) {
                extras.getParcelable<PokemonModelView>(Constants.POKEMON_DETAIL)?.let { it ->
                    viewModel.setupPokemonDetail(it)
                }
            }
        }
    }

    private fun setuoObservers() {
        viewModel.pokemon.observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(pokemon: PokemonModelView) {
        binding.apply {
            tvName.text = pokemon.name
            tvWeight.text = resources.getString(R.string.detail_weight) + pokemon.weight
            tvHeight.text = resources.getString(R.string.detail_height) + pokemon.height
            tvCatchedTo.text = resources.getString(R.string.detail_catched) + pokemon.dateCatched
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
