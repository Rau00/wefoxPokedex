package technical.test.pokedex.data.exceptions

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Pokemon not found")
