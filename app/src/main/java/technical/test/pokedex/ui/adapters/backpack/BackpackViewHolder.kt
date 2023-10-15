package technical.test.pokedex.ui.adapters.backpack

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import technical.test.pokedex.data.models.view.PokemonModelView
import technical.test.pokedex.databinding.ItemBackpackPokemonBinding

class BackpackViewHolder(private val contentView: ItemBackpackPokemonBinding, val action: (PokemonModelView) -> Unit)
    : RecyclerView.ViewHolder(contentView.root) {

    fun bindView(pokemon: PokemonModelView) {

        contentView.backpackPokemonName.text = pokemon.name
        Glide.with(contentView.root.context)
            .load(pokemon.sprite)
            .into(contentView.backpackPokemonImage)
        contentView.root.setOnClickListener {
            action(pokemon)
        }
    }
}