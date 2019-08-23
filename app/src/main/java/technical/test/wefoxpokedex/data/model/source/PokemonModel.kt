package technical.test.wefoxpokedex.data.model.source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import technical.test.wefoxpokedex.utils.constans.Constants

@JsonClass(generateAdapter = true)
@Entity(tableName = Constants.PERSISTENCE_TABLE)
data class PokemonModel(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: PokemonSprites,
    var dateCatched: String = "",
    @Json(name = "base_experience") val baseExperience: Int,
    val types: List<PokemonType>
)

