package technical.test.pokedex.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprite: String,
    val dateCaught: String = "",
    val baseExperience: Int,
    val types: List<String>
): Parcelable