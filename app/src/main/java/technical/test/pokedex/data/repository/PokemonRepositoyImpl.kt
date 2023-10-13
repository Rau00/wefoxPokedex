package technical.test.pokedex.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import technical.test.pokedex.data.model.source.PokemonModel
import technical.test.pokedex.data.model.view.PokemonModelView
import technical.test.pokedex.data.network.datasource.RemoteDataSource
import technical.test.pokedex.data.network.model.ResultData
import technical.test.pokedex.data.persistence.datasource.PersistenceDataSource
import technical.test.pokedex.utils.RandomUtils
import technical.test.pokedex.data.model.converters.ConvertModelDataToModelView
import technical.test.pokedex.data.model.source.mapper.toModel
import technical.test.pokedex.data.model.userCase.PokemonUserCaseModel
import technical.test.pokedex.utils.constans.Constants
import java.text.SimpleDateFormat
import java.util.*

class PokemonRepositoyImpl(
    private val remoteDataSource: RemoteDataSource,
    private val daoDataSource: PersistenceDataSource
) : PokemonRepository {

    private val RANDOM_MIN = 1
    private val RANDOM_MAX = 1000

    override val pokemonUsercase: MutableLiveData<PokemonUserCaseModel> = MutableLiveData()

    override var pokemonFound: PokemonModel? = null
    override lateinit var pokemonBackpack: List<PokemonModel>

    override suspend fun searchPokemon() {
        val pokemon = remoteDataSource.getPokemon(
            RandomUtils.getRamdonNumer(RANDOM_MIN, RANDOM_MAX))

        when (pokemon) {
            is ResultData.Success -> {
                pokemonFound = pokemon.data.toModel()
                pokemonFound?.let {
                    pokemonUsercase.postValue(PokemonUserCaseModel
                        .PokemonFounded(ConvertModelDataToModelView.dataToViewModel(it)))
                }
            }
            is ResultData.Error -> {
                pokemonUsercase.postValue(PokemonUserCaseModel.ErrorDataFound(pokemon.exception.message!!))
            }
        }
    }

    override suspend fun getBackpack() {
        pokemonBackpack = daoDataSource.getPokemonsCatched()
        if (pokemonBackpack.isNullOrEmpty()) {
            pokemonUsercase.postValue(PokemonUserCaseModel.BackpackEmpty(true))
        } else {
            pokemonUsercase.postValue(PokemonUserCaseModel.PokemonsCatched(convertToViewModel()))
        }
    }

    override fun pokemonCatched() {
        runBlocking {
            pokemonFound?.run {
                this.dateCatched = Date().getCurrentDate()
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

    fun Date.getCurrentDate(): String {
        val sdf = SimpleDateFormat(Constants.FORMAT_TIME, Locale.ENGLISH)
        return sdf.format(Date())
    }
}