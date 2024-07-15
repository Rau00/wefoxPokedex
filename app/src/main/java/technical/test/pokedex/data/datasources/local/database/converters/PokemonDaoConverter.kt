package technical.test.pokedex.data.datasources.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import technical.test.pokedex.data.datasources.local.entities.PokemonStatEntity
import java.lang.reflect.Type


class PokemonDaoConverter {

    @TypeConverter
    fun fromPokemonTypes(value: List<String>): String? {
        val pokemonTypes: Type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(value, pokemonTypes)
    }

    @TypeConverter
    fun stringToPokemonTypes(value: String): List<String>? {
        val pokemonTypes: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, pokemonTypes)
    }

    @TypeConverter
    fun fromPokemonStats(value: List<PokemonStatEntity>): String? {
        val pokemonStats: Type = object : TypeToken<List<PokemonStatEntity>>() {}.type
        return Gson().toJson(value, pokemonStats)
    }

    @TypeConverter
    fun stringToPokemonStats(value: String): List<PokemonStatEntity>? {
        val pokemonStats: Type = object : TypeToken<List<PokemonStatEntity>>() {}.type
        return Gson().fromJson(value, pokemonStats)
    }
}