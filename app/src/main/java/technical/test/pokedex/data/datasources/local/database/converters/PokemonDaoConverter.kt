package technical.test.pokedex.data.datasources.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class PokemonDaoConverter {

    @TypeConverter
    fun fromPokemonTypes(value: List<String>): String? {
        val pokemontypes: Type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(value, pokemontypes)
    }

    @TypeConverter
    fun stringToPokemonTypes(value: String): List<String>? {
        val pokemontypes: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, pokemontypes)
    }
}