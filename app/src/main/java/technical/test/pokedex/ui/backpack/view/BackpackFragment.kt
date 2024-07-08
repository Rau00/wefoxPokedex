package technical.test.pokedex.ui.backpack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import technical.test.pokedex.R
import technical.test.pokedex.databinding.BackpackFragmentBinding
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.ui.adapters.backpack.BackpackAdapter
import technical.test.pokedex.ui.backpack.viewmodel.BackpackViewModel
import technical.test.pokedex.ui.components.dialog.DialogComponent

@AndroidEntryPoint
class BackpackFragment : Fragment() {

    companion object {
        fun newInstance() = BackpackFragment()
    }

    private val viewModel: BackpackViewModel by viewModels()
    private lateinit var binding: BackpackFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BackpackFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.lifecycleScope?.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.updateBackpack()
                setupObservers()
            }
        }
        setupListener()
    }

    private fun setupListener() {
        binding.fabHunting.setOnClickListener {
            viewModel.goHunting(requireActivity())
        }

        binding.fabSort.setOnClickListener {
            viewModel.sortAlphabetical()
        }
    }

    private suspend fun setupObservers() {
        viewModel.pokemonBackpackResult.collect { pokemonStateResult ->
            when (pokemonStateResult) {
                PokemonViewStates.Idle -> {
                    viewModel.getBackpack()
                }
                PokemonViewStates.Loading -> {

                }
                is PokemonViewStates.PokemonCaughtList -> {
                    setupAdapter(pokemonStateResult.pokemonCaughtList)
                }

                is PokemonViewStates.BackpackEmpty -> {
                    showDialogBackpackEmpty()
                }

                else -> {}
            }
        }
    }

    private fun setupAdapter(backpackPokemon: List<PokemonModel>) {
        binding.rvBackpack.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = BackpackAdapter(backpackPokemon) { viewModel.seePokemonDetail(requireActivity(), it) }
        }
    }

    private fun showDialogBackpackEmpty() {
        context?.let { context ->
            val dialogBackpackEmpty = DialogComponent(context)
            val resources = context.resources
            dialogBackpackEmpty.createDialog(
                title = resources.getString(R.string.title_backpack_empty),
                message = resources.getString(R.string.message_backpack_empty)
            )
            dialogBackpackEmpty.setupActions(
                textPositive = resources.getString(R.string.positive_backpack_empty),
                actionPositive = { viewModel.goHunting(requireActivity()) },
                textNegative = resources.getString(R.string.negative_backpack_empty)
            )
            dialogBackpackEmpty.showDialog()
        }

    }

}
