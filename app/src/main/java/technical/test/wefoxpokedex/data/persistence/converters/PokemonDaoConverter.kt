package technical.test.wefoxpokedex.data.persistence.converters

import androidx.room.TypeConverter
import technical.test.wefoxpokedex.data.model.source.PokemonSprites
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import technical.test.wefoxpokedex.data.model.source.PokemonType


class PokemonDaoConverter {
    @TypeConverter
    fun stringToPokemonSprite(value: String): PokemonSprites {
        return PokemonSprites(value)
    }

    @TypeConverter
    fun fromPokemonSprite(value: PokemonSprites): String? {
        return value.front_default
    }

    @TypeConverter
    fun fromPokemonTypes(value: List<PokemonType>): String? {
        val moshi = Moshi.Builder().build()
        val pokemontypes = Types.newParameterizedType(List::class.java, PokemonType::class.java)
        val jsonAdapter = moshi.adapter<List<PokemonType>>(pokemontypes)
        return jsonAdapter.toJson(value)
    }

    @TypeConverter
    fun stringToPokemonTypes(value: String): List<PokemonType>? {
        val moshi = Moshi.Builder().build()
        val pokemontypes = Types.newParameterizedType(List::class.java, PokemonType::class.java)
        val jsonAdapter = moshi.adapter<List<PokemonType>>(pokemontypes)
        return jsonAdapter.fromJson(value)
    }
}