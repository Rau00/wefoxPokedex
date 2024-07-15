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
    val types: List<String>,
    val stats: List<PokemonStats>
): Parcelable {
    fun calculateGeneration(): Int {
        return when {
            order in 1..151 -> 1
            order in 152..251 -> 2
            order in 252..386 -> 3
            order in 387..493 -> 4
            order in 494..649 -> 5
            order in 650..721 -> 6
            order in 722..809 -> 7
            order in 810..898 -> 8
            else -> 0
        }
    }
}

@Parcelize
data class PokemonStats(
    val name: String,
    val baseStat: Int,
): Parcelable