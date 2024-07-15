package technical.test.pokedex.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import technical.test.pokedex.Constants
import java.util.Calendar


@Entity(tableName = Constants.PERSISTENCE_TABLE)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: String,
    var dateCaught: String = Calendar.getInstance().time.toString(),
    val baseExperience: Int,
    val types: List<String>,
    val stats: List<PokemonStatEntity>
)

@Entity(tableName = Constants.PERSISTENCE_STATS_TABLE)
data class PokemonStatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val baseStat: Int,
    val name: String
)