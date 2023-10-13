package technical.test.pokedex.data.persistence.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import technical.test.pokedex.data.model.source.PokemonSprites
import technical.test.pokedex.data.model.source.PokemonType
import java.lang.reflect.Type


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
        val pokemontypes: Type = object : TypeToken<List<PokemonType>>() {}.type
        return Gson().toJson(value, pokemontypes)
    }

    @TypeConverter
    fun stringToPokemonTypes(value: String): List<PokemonType>? {
        val pokemontypes: Type = object : TypeToken<List<PokemonType>>() {}.type
        return Gson().fromJson(value, pokemontypes)
    }
}