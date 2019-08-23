package technical.test.wefoxpokedex.ui.pokemondetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import technical.test.wefoxpokedex.R
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import technical.test.wefoxpokedex.ui.pokemondetail.viewmodel.PokemonDetailViewModelImpl
import technical.test.wefoxpokedex.utils.constans.Constants

class PokemonDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    private lateinit var viewModel: PokemonDetailViewModelImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pokemon_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        getPokemon()
        setuoObservers()
    }

    private fun getPokemon() {
        activity?.let {
            if (it.intent.extras.containsKey(Constants.POKEMON_DETAIL)) {
                viewModel.setupPokemonDetail(it.intent.extras.getParcelable(Constants.POKEMON_DETAIL))
            }
        }
    }

    private fun setuoObservers() {
        viewModel.pokemon.observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(pokemon: PokemonModelView) {
        tvName.text = pokemon.name
        tvWeight.text = resources.getString(R.string.detail_weight) + pokemon.weight
        tvHeight.text = resources.getString(R.string.detail_height) + pokemon.height
        tvCatchedTo.text = resources.getString(R.string.detail_catched) + pokemon.dateCatched
        tvExperience.text = resources.getString(R.string.detail_experience) + pokemon.baseExperience
        addTypesView(pokemon.types)
        context?.let { context ->
            Glide.with(context)
                .load(pokemon.sprite)
                .into(ivpokemonFounded)
        }
    }

    private fun addTypesView(types: List<String>) {
        tvTypes.addView(createTypeTextView(resources.getString(R.string.detail_type)))
        for (type in types) {
            tvTypes.addView(createTypeTextView(type))
        }
    }

    private fun createTypeTextView(value: String): TextView {
        val typeView = TextView(context)
        typeView.text = value
        return typeView
    }
}
