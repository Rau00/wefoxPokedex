package technical.test.pokedex.ui.adapters.backpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import technical.test.pokedex.data.models.view.PokemonModelView
import technical.test.pokedex.databinding.ItemBackpackPokemonBinding

class BackpackAdapter(private val backpackList: List<PokemonModelView>, private val action: (PokemonModelView) -> Unit):
    RecyclerView.Adapter<BackpackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackpackViewHolder =
        BackpackViewHolder(getLayout(parent), action)

    override fun getItemCount(): Int = backpackList.size

    override fun onBindViewHolder(holder: BackpackViewHolder, position: Int) {
        holder.bindView(backpackList[position])
    }

    private fun getLayout(parent: ViewGroup): ItemBackpackPokemonBinding {
        return ItemBackpackPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }
}