package technical.test.wefoxpokedex.ui.adapters.backpack

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_backpack_pokemon.view.*
import technical.test.wefoxpokedex.data.model.view.PokemonModelView

class BackpackViewHolder(itemView: View, val action: (PokemonModelView) -> Unit)
    : RecyclerView.ViewHolder(itemView) {

    fun bindView(pokemon: PokemonModelView) {
        itemView.backpackPokemonName.text = pokemon.name
        Glide.with(itemView.context)
            .load(pokemon.sprite)
            .into(itemView.backpackPokemonImage)
        itemView.setOnClickListener {
            action(pokemon)
        }
    }
}