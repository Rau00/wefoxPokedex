package technical.test.pokedex.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import technical.test.pokedex.utils.constans.Constants
import java.util.Calendar


@Entity(tableName = Constants.PERSISTENCE_TABLE)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: String,
    var dateCaught: String = Calendar.getInstance().time.toString(),
    val baseExperience: Int,
    val types: List<String>
)