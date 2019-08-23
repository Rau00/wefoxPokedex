package technical.test.wefoxpokedex.ui.backpack.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.backpack_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

import technical.test.wefoxpokedex.R
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import technical.test.wefoxpokedex.ui.adapters.backpack.BackpackAdapter
import technical.test.wefoxpokedex.ui.backpack.viewmodel.BackpackViewModelImpl
import technical.test.wefoxpokedex.ui.components.dialog.DialogComponent

class BackpackFragment : Fragment() {

    companion object {
        fun newInstance() = BackpackFragment()
    }

    private lateinit var viewModel: BackpackViewModelImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.backpack_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupObservers()
        setupListener()
        viewModel.getbackpack()
    }

    private fun setupViewModel() {
        viewModel = getViewModel()
        activity?.let { viewModel.initRouter(activity as Activity) }
    }

    private fun setupListener() {
        fabHunting.setOnClickListener {
            viewModel.goHunting()
        }
    }

    private fun setupObservers() {
        viewModel.pokemonCatched.observe(viewLifecycleOwner, Observer {
            setupAdapter(it)
        })

        viewModel.backpackEmpty.observe(viewLifecycleOwner, Observer {
            showDialogBackpackEmpty()
        })
    }

    private fun setupAdapter(backpackPokemon: List<PokemonModelView>) {
        rvBackpack.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = BackpackAdapter(backpackPokemon) { viewModel.seePokemonDetail(it) }
        }
    }

    private fun showDialogBackpackEmpty() {
        context?.let {context ->
            val dialogBackpackEmpty = DialogComponent(context)
            val resources = context.resources
            dialogBackpackEmpty.createDialog(
                title = resources.getString(R.string.title_backpack_empty),
                message = resources.getString(R.string.message_backpack_empty))
            dialogBackpackEmpty.setupActions(
                textPositive =  resources.getString(R.string.positive_backpack_empty),
                actionPositive = { viewModel.goHunting() },
                textNegative = resources.getString(R.string.negative_backpack_empty))
            dialogBackpackEmpty.showDialog()
        }

    }

}
