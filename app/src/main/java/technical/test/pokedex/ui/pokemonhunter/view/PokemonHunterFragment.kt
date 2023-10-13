package technical.test.pokedex.ui.pokemonhunter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.getViewModel
import technical.test.pokedex.R
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel
import technical.test.pokedex.data.model.view.PokemonModelView
import technical.test.pokedex.databinding.BackpackFragmentBinding
import technical.test.pokedex.databinding.PokemonHunterFragmentBinding
import technical.test.pokedex.ui.pokemonhunter.viewmodel.PokemonHunterViewModelImpl

class PokemonHunterFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonHunterFragment()
    }

    private lateinit var viewModel: PokemonHunterViewModelImpl
    private lateinit var binding: PokemonHunterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonHunterFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
                else -> {}
            }
        })

        viewModel.isCatched.observe(viewLifecycleOwner, Observer {
            binding.btCatch.visibility = it
        })
    }

    private fun setupListeners() {
        binding.apply {
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
    }

    private fun render(pokemon: PokemonModelView) {
        binding.apply {
            tvName.text = pokemon.name
            tvWeight.text = resources.getString(R.string.hunt_weight) + pokemon.weight
            tvHeight.text = resources.getString(R.string.hunt_height) + pokemon.height
            context?.let { context ->
                Glide.with(context)
                    .load(pokemon.sprite)
                    .into(ivpokemonFounded)
            }
        }
    }

    private fun showError(message: String) {
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
    }

    private fun hideError() {
        binding.tvError.visibility = View.GONE
    }

    private fun hidePokemonInfo() {
        binding.containerPokemon.visibility = View.GONE
    }

    private fun showPokemonInfo() {
        binding.containerPokemon.visibility = View.VISIBLE
    }

    private fun chageToSearchButton() {
        binding.apply {
            btLeaveIt.visibility = View.GONE
            btCatch.visibility = View.GONE
            btSearch.visibility = View.VISIBLE
        }
    }

    private fun changeToCatchLeaveButtons() {
        binding.apply {
            btLeaveIt.visibility = View.VISIBLE
            btCatch.visibility = View.VISIBLE
            btSearch.visibility = View.GONE
        }
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun enableButtons() {
        binding.apply {
            btLeaveIt.isEnabled = true
            btCatch.isEnabled = true
            btSearch.isEnabled = true
        }
    }

    private fun disableButtons() {
        binding.apply {
            btLeaveIt.isEnabled = false
            btCatch.isEnabled = false
            btSearch.isEnabled = false
        }
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
        binding.ivpokemonFounded.setImageDrawable(
            resources.getDrawable(R.drawable.pokeball2))
    }

}
