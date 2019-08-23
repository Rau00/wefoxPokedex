package technical.test.wefoxpokedex.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import technical.test.wefoxpokedex.data.exceptions.RemoteDataNotFoundException
import technical.test.wefoxpokedex.data.model.source.PokemonModel
import technical.test.wefoxpokedex.data.model.view.PokemonModelView
import technical.test.wefoxpokedex.data.network.datasource.RemoteDataSource
import technical.test.wefoxpokedex.data.network.model.ResultData
import technical.test.wefoxpokedex.data.persistence.datasource.PersistenceDataSource
import technical.test.wefoxpokedex.utils.RandomUtils
import technical.test.wefoxpokedex.utils.TimeUtils
import technical.test.wefoxpokedex.utils.view.ConvertModelDataToModelView
import java.lang.IllegalArgumentException

class PokemonRepositoyImpl(
    private val remoteDataSource: RemoteDataSource,
    private val daoDataSource: PersistenceDataSource
) : PokemonRepository {

    private val RANDOM_MIN = 1
    private val RANDOM_MAX = 1000

    override val pokemonFounded: MutableLiveData<PokemonModelView> = MutableLiveData()
    override val pokemonCatched: MutableLiveData<MutableList<PokemonModelView>> = MutableLiveData()
    override val backpackEmpty: MutableLiveData<Boolean> = MutableLiveData()
    override val errorDataFound: MutableLiveData<String> = MutableLiveData()

    private var pokemonFound: PokemonModel? = null
    private lateinit var pokemonBackpack: List<PokemonModel>

    override suspend fun searchPokemon() {
        try {
            val pokemon = remoteDataSource.getPokemon(
                RandomUtils.getRamdonNumer(RANDOM_MIN, RANDOM_MAX))
            if (pokemon is ResultData.Success) {
                pokemonFound = pokemon.data
                pokemonFound?.let {
                    pokemonFounded.postValue(ConvertModelDataToModelView.dataToViewModel(it))
                }
            }
        } catch (noData: RemoteDataNotFoundException) {
            errorDataFound.postValue(noData.message)
        } catch (e: HttpException) {
            errorDataFound.postValue(e.message)
        } catch (e: IllegalArgumentException) {
            errorDataFound.postValue(e.message)
        }
    }

    override suspend fun getBackpack() {
        pokemonBackpack = daoDataSource.getPokemonsCatched()
        if (pokemonBackpack.isNullOrEmpty()) {
            backpackEmpty.postValue(true)
        } else {
            pokemonCatched.postValue(convertToViewModel())
        }
    }

    override fun pokemonCatched() {
        runBlocking {
            pokemonFound?.run {
                this.dateCatched = TimeUtils.getCurrentDate()
                daoDataSource.storePokemonCatched(this)
            }
        }
    }

    override fun setFreePokemon(id: Int) {
        runBlocking {
            daoDataSource.setFreePokemonCatched(id)
        }
    }

    override fun setFreeAllPokemon() {
        runBlocking {
            daoDataSource.setFreeAllPokemon()
        }
    }

    private fun convertToViewModel(): MutableList<PokemonModelView> {
      return ConvertModelDataToModelView.dataToViewModelList(sortByOrder())
    }

    private fun sortByOrder(): List<PokemonModel> {
        return pokemonBackpack.sortedWith(compareBy { it.order })
    }


}