package technical.test.pokedex.data.model.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PokemonModelView(
    val name: String,
    val weight: String,
    val height: String,
    val sprite: String,
    val dateCatched: String,
    val baseExperience: String,
    val types: List<String>
): Parcelable