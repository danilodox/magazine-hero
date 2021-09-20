package com.br.magazinehero.data.network

import com.br.magazinehero.data.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {

    // Vou deixar a URL e a API KEY exposta apenas para facilitar para o time de avaliação.
    companion object {

        const val BASE_URL = "https://gateway.marvel.com/v1/public/"

        val interceptor = AuthInterceptor()


    }




    private fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
                .apply {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(interceptor)
                    addInterceptor(logging)
                }
            .build()

    var service: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(ApiService::class.java)

}