package technical.test.pokedex.utils

import kotlin.random.Random

class RandomUtils {

    companion object {
        fun getRamdonNumer(min: Int, max: Int): Int {
            return Random.nextInt(min, max)
        }
    }
}