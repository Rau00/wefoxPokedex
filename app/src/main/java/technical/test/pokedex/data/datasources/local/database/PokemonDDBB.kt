package technical.test.pokedex.data.datasources.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.local.database.converters.PokemonDaoConverter
import technical.test.pokedex.utils.constans.Constants

@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(PokemonDaoConverter::class)
abstract class PokemonDDBB : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDDBB? = null

        fun getDatabase(context: Context): PokemonDDBB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDataBase(context).also { INSTANCE = it }
            }
        }

        private fun createDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PokemonDDBB::class.java,
            Constants.PERSISTENCE_TABLE
        ).build()

    }
}