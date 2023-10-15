package technical.test.pokedex.ui.backpack.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import technical.test.pokedex.R
import technical.test.pokedex.ui.PokemonViewStates
import technical.test.pokedex.databinding.BackpackFragmentBinding
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.ui.adapters.backpack.BackpackAdapter
import technical.test.pokedex.ui.backpack.viewmodel.BackpackViewModel
import technical.test.pokedex.ui.components.dialog.DialogComponent

class BackpackFragment : Fragment() {

    companion object {
        fun newInstance() = BackpackFragment()
    }

    private lateinit var viewModel: BackpackViewModel
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
        setupViewModel()
        activity?.lifecycleScope?.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                setupObservers()
            }
        }
        setupListener()
    }

    private fun setupViewModel() {
        viewModel = getViewModel()
        activity?.let { viewModel.initRouter(activity as Activity) }
    }

    private fun setupListener() {
        binding.fabHunting.setOnClickListener {
            viewModel.goHunting()
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
            adapter = BackpackAdapter(backpackPokemon) { viewModel.seePokemonDetail(it) }
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
                actionPositive = { viewModel.goHunting() },
                textNegative = resources.getString(R.string.negative_backpack_empty)
            )
            dialogBackpackEmpty.showDialog()
        }

    }

}
