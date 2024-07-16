package technical.test.pokedex.domain.models

import android.app.Activity
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import technical.test.pokedex.ai.PokemonAI

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
        return when(id) {
            in 1..151 -> 1
            in 152..251 -> 2
            in 252..386 -> 3
            in 387..493 -> 4
            in 494..649 -> 5
            in 650..721 -> 6
            in 722..809 -> 7
            in 810..905 -> 8
            in 906..1026 -> 9
            else -> 0
        }
    }

    fun getVictoryProbability(activity: Activity): Float {
        val pokemonAI = PokemonAI(activity)
        val inputModelPreprocessed= pokemonAI.floatArrayToBuffer(floatArrayOf(
            stats[0].baseStat.toFloat(),
            stats[1].baseStat.toFloat(),
            stats[2].baseStat.toFloat(),
            stats[3].baseStat.toFloat(),
            stats[4].baseStat.toFloat(),
            stats[5].baseStat.toFloat(),
            calculateGeneration().toFloat()))

        val victoryProbability = pokemonAI.predict(inputModelPreprocessed)

        return victoryProbability
    }
}

@Parcelize
data class PokemonStats(
    val name: String,
    val baseStat: Int,
): Parcelable