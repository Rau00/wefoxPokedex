package technical.test.pokedex.data.model.source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import technical.test.pokedex.utils.constans.Constants


@Entity(tableName = Constants.PERSISTENCE_TABLE)
data class PokemonModel(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val order: Int,
    val sprites: PokemonSprites,
    var dateCatched: String = "",
    @SerializedName("base_experience") val baseExperience: Int,
    val types: List<PokemonType>
)

