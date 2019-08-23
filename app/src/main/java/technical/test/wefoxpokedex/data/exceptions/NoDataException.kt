package technical.test.wefoxpokedex.data.exceptions

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Pokemon not found")
