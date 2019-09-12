package technical.test.wefoxpokedex.ui.pokemonhunter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pokemon_hunter_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import technical.test.wefoxpokedex.R
import technical.test.wefoxpokedex.data.model.userCase.PokemonUserCaseModel
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import technical.test.wefoxpokedex.ui.pokemonhunter.viewmodel.PokemonHunterViewModelImpl

class PokemonHunterFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonHunterFragment()
    }

    private lateinit var viewModel: PokemonHunterViewModelImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pokemon_hunter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        setupObservers()
        setupListeners()
        searchPokemon()
    }

    private fun setupObservers() {
        viewModel.pokemonUsercase.observe(viewLifecycleOwner, Observer { pokemonUserCase ->
            when(pokemonUserCase) {
                is PokemonUserCaseModel.PokemonFounded -> {
                    foundedPokemon(pokemonUserCase.pokemonFunded)}
                is PokemonUserCaseModel.ErrorDataFound -> { notFoundedPokemon(pokemonUserCase.errorMessage)}
            }
        })

        viewModel.isCatched.observe(viewLifecycleOwner, Observer {
            btCatch.visibility = it
        })
    }

    private fun setupListeners() {
        btCatch.setOnClickListener {
            catchPokemon()
        }

        btLeaveIt.setOnClickListener {
            activity?.let { it.finish() }
        }

        btSearch.setOnClickListener {
            searchPokemon()
            hideError()
        }
    }

    private fun render(pokemon: PokemonModelView) {
        tvName.text = pokemon.name
        tvWeight.text = resources.getString(R.string.hunt_weight) + pokemon.weight
        tvHeight.text = resources.getString(R.string.hunt_height) + pokemon.height
        context?.let { context ->
            Glide.with(context)
                .load(pokemon.sprite)
                .into(ivpokemonFounded)
        }
    }

    private fun showError(message: String) {
        tvError.visibility = View.VISIBLE
        tvError.text = message
    }

    private fun hideError() {
        tvError.visibility = View.GONE
    }

    private fun hidePokemonInfo() {
        containerPokemon.visibility = View.GONE
    }

    private fun showPokemonInfo() {
        containerPokemon.visibility = View.VISIBLE
    }

    private fun chageToSearchButton() {
        btLeaveIt.visibility = View.GONE
        btCatch.visibility = View.GONE
        btSearch.visibility = View.VISIBLE
    }

    private fun changeToCatchLeaveButtons() {
        btLeaveIt.visibility = View.VISIBLE
        btCatch.visibility = View.VISIBLE
        btSearch.visibility = View.GONE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun enableButtons() {
        btLeaveIt.isEnabled = true
        btCatch.isEnabled = true
        btSearch.isEnabled = true
    }

    private fun disableButtons() {
        btLeaveIt.isEnabled = false
        btCatch.isEnabled = false
        btSearch.isEnabled = false
    }

    private fun searchPokemon() {
        showLoading()
        disableButtons()
        viewModel.searchPokemon()
    }

    private fun foundedPokemon(pokemon: PokemonModelView) {
        enableButtons()
        changeToCatchLeaveButtons()
        hideError()
        hideLoading()
        render(pokemon)
        viewModel.checkPokemonCatched()
        showPokemonInfo()
    }

    private fun notFoundedPokemon(error: String) {
        enableButtons()
        showError(error)
        chageToSearchButton()
        hidePokemonInfo()
        hideLoading()
    }

    private fun catchPokemon() {
        disableButtons()
        showLoading()
        viewModel.catchPokemon()
        ivpokemonFounded.setImageDrawable(
            resources.getDrawable(R.drawable.pokeball2))
    }

}
