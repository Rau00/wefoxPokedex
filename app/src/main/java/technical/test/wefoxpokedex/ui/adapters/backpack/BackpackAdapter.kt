package technical.test.wefoxpokedex.ui.adapters.backpack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import technical.test.wefoxpokedex.R
import technical.test.wefoxpokedex.data.model.view.PokemonModelView

class BackpackAdapter(private val backpackList: List<PokemonModelView>, private val action: (PokemonModelView) -> Unit):
    RecyclerView.Adapter<BackpackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackpackViewHolder =
        BackpackViewHolder(getLayout(parent), action)

    override fun getItemCount(): Int = backpackList.size

    override fun onBindViewHolder(holder: BackpackViewHolder, position: Int) {
        holder.bindView(backpackList[position])
    }

    private fun getLayout(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(
            R.layout.item_backpack_pokemon, parent, false)
    }
}