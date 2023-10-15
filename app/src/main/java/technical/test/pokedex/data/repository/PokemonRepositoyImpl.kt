package technical.test.pokedex.data.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import technical.test.pokedex.data.datasources.local.PokemonLocalDataSource
import technical.test.pokedex.data.datasources.local.entities.PokemonEntity
import technical.test.pokedex.data.datasources.remote.RemoteDataSource
import technical.test.pokedex.data.datasources.remote.network.model.ResultData
import technical.test.pokedex.domain.PokemonUserCaseModel
import technical.test.pokedex.domain.models.PokemonModel
import technical.test.pokedex.domain.models.mapper.toModel
import technical.test.pokedex.utils.RandomUtils
import technical.test.pokedex.utils.constans.Constants
import java.text.SimpleDateFormat
import java.util.*

class PokemonRepositoyImpl(
    private val remoteDataSource: RemoteDataSource,
    private val daoDataSource: PokemonLocalDataSource
) : PokemonRepository {

    private val RANDOM_MIN = 1
    private val RANDOM_MAX = 1000

    override val pokemonUsercase: MutableLiveData<PokemonModel> = MutableLiveData()

    override var pokemonFound: PokemonEntity? = null
    override lateinit var pokemonBackpack: List<PokemonEntity>

    override suspend fun searchPokemon() {
        val pokemon = remoteDataSource.getPokemon(
            RandomUtils.getRamdonNumer(RANDOM_MIN, RANDOM_MAX)
        )

        when (pokemon) {
            is ResultData.Success -> {
                pokemonFound = pokemon.data.toModel()
                pokemonFound?.let {
                    pokemonUsercase.postValue(
                        PokemonUserCaseModel
                            .PokemonFounded(ConvertModelDataToModelView.dataToViewModel(it))
                    )
                }
            }

            is ResultData.Error -> {
                pokemonUsercase.postValue(PokemonUserCaseModel.ErrorDataFound(pokemon.exception.message!!))
            }
        }
    }

    override suspend fun getBackpack() {
       daoDataSource.getPokemonsCatched()
            .onSuccess {
                pokemonBackpack = it
                if (it.isNullOrEmpty()) {
                    pokemonUsercase.postValue(PokemonUserCaseModel.BackpackEmpty(true))
                } else {
                    pokemonUsercase.postValue(PokemonUserCaseModel.PokemonsCatched(convertToViewModel(it)))
                }
            }

    }

    override suspend fun pokemonCatched() {
        pokemonFound?.run {
            this.dateCaught = Date().getCurrentDate()
            daoDataSource.storePokemonCatched(this)
        }
    }

    override suspend fun setFreePokemon(id: Int) {
        runBlocking {
            daoDataSource.setFreePokemonCatched(id)
        }
    }

    override suspend fun setFreeAllPokemon() {
        runBlocking {
            daoDataSource.setFreeAllPokemon()
        }
    }

    private fun convertToViewModel(pokemonBackpack: List<PokemonEntity>): MutableList<PokemonModelView> {
        return ConvertModelDataToModelView.dataToViewModelList(sortByOrder(pokemonBackpack))
    }

    private fun sortByOrder(pokemonBackpack: List<PokemonEntity>): List<PokemonEntity> {
        return pokemonBackpack.sortedWith(compareBy { it.order })
    }

    fun Date.getCurrentDate(): String {
        val sdf = SimpleDateFormat(Constants.FORMAT_TIME, Locale.ENGLISH)
        return sdf.format(Date())
    }
}