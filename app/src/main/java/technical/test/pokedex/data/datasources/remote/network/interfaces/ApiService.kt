package technical.test.pokedex.data.datasources.remote.network.interfaces

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import technical.test.pokedex.utils.constans.Constants
import java.util.concurrent.TimeUnit

interface ApiService {

    companion object Factory {

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder().apply {
                client(makeOkHttpClient())
                baseUrl(Constants.URL_BASE)
                addConverterFactory(GsonConverterFactory.create())
            }.build()

            return retrofit.create(ApiInterface::class.java)
        }

        private fun makeOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(makeLoggingInterceptor()).apply {
                    connectTimeout(120, TimeUnit.SECONDS)
                    readTimeout(120, TimeUnit.SECONDS)
                    writeTimeout(90, TimeUnit.SECONDS)
                }.build()
        }

        private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level =
                HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }
}