package technical.test.pokedex.data.datasources.remote.network.model

sealed class ResultData<out T : Any> {

    class Success<out T : Any>(val data: T) : ResultData<T>()

    class Error(val exception: Throwable) : ResultData<Nothing>()
}