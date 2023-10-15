package technical.test.pokedex.data.datasources.remote.network.exceptions

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Pokemon not found")
