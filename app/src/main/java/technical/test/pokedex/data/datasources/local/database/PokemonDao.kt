package technical.test.pokedex.data.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.Constants

@Dao
interface PokemonDao {

    @Query("SELECT * from ${Constants.PERSISTENCE_TABLE}")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Insert
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Query("DELETE FROM ${Constants.PERSISTENCE_TABLE}")
    fun deleteAllPokemon()

    @Query("DELETE FROM ${Constants.PERSISTENCE_TABLE} WHERE id = :pokemonId")
    fun deletePokemon(pokemonId: Int)
}