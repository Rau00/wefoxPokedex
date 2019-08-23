package technical.test.wefoxpokedex.data.persistence.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.utils.constans.Constants

@Dao
interface PokemonDao {

    @Query("SELECT * from ${Constants.PERSISTENCE_TABLE}")
    fun getAllPokemon(): List<PokemonModel>

    @Insert
    suspend fun insertPokemon(pokemon: PokemonModel)

    @Query("DELETE FROM ${Constants.PERSISTENCE_TABLE}")
    fun deleteAllPokemon()

    @Query("DELETE FROM ${Constants.PERSISTENCE_TABLE} WHERE id = :pokemonId")
    fun deletePokemon(pokemonId: Int)
}