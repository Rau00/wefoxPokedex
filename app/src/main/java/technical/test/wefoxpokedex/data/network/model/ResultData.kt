package technical.test.wefoxpokedex.data.network.model

sealed class ResultData<out T : Any> {

    class Success<out T : Any>(val data: T) : ResultData<T>()

    class Error(val exception: Throwable) : ResultData<Nothing>()
}