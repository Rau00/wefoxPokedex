package technical.test.pokedex.ui.pokemonhunter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import technical.test.pokedex.R
import technical.test.pokedex.databinding.PokemonHunterFragmentBinding
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.pokemonhunter.viewmodel.PokemonHunterViewModel

@AndroidEntryPoint
class PokemonHunterFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonHunterFragment()
    }

    private val viewModel: PokemonHunterViewModel by viewModels()
    private lateinit var binding: PokemonHunterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonHunterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                setupObservers()
            }
        }
    }

    private suspend fun setupObservers() {
        viewModel.pokemonFound.collect { pokemonFountResult ->
            when (pokemonFountResult) {
                PokemonViewStates.Idle -> {
                    searchPokemon()
                }
                PokemonViewStates.Loading -> {
                    showLoading()
                }
                is PokemonViewStates.PokemonFounded -> {
                    hideLoading()
                    enableButtons()
                    foundedPokemon(pokemonFountResult.pokemonFunded)
                }

                is PokemonViewStates.ErrorDataFound -> {
                    hideLoading()
                    enableButtons()
                    notFoundedPokemon(pokemonFountResult.errorMessage)
                }

                else -> {}
            }
        }

        viewModel.isPokemonCaught.collect { isCaught ->
            if (isCaught) {
                binding.btCatch.visibility = View.GONE
            } else {
                binding.btCatch.visibility = View.VISIBLE
            }
        }
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

    private fun render(pokemon: PokemonModel) {
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
        disableButtons()
        viewModel.searchPokemon()
    }

    private fun foundedPokemon(pokemon: PokemonModel) {
        enableButtons()
        changeToCatchLeaveButtons()
        hideError()
        hideLoading()
        render(pokemon)
        viewModel.checkPokemonCaught()
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
            resources.getDrawable(R.drawable.pokeball2)
        )
    }

}
