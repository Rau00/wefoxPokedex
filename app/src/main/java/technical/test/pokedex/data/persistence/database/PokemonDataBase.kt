package technical.test.pokedex.data.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.persistence.converters.PokemonDaoConverter
import technical.test.pokedex.utils.constans.Constants

@Database(entities = [PokemonModel::class], version = 1)
@TypeConverters(PokemonDaoConverter::class)
abstract class PokemonDataBase: RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDataBase? = null

        fun getDatabase(context: Context): PokemonDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDataBase(context).also { INSTANCE = it }
            }
        }

        private fun createDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PokemonDataBase::class.java,
            Constants.PERSISTENCE_TABLE
        ).build()

    }
}