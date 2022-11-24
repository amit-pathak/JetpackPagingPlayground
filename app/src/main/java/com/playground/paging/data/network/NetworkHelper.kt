package com.playground.paging.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class  NetworkHelper @Inject constructor() {

    private val timeOut: Long = 120
    private val url = "https://rickandmortyapi.com/"
    private val gson = GsonBuilder().setLenient().create()

    fun getRetrofit() = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(getOkHttp().build())
        .build()

    private fun getOkHttp(): OkHttpClient.Builder {
        return getOkHttpRaw()//.addInterceptor(AuthenticationInterceptorRefreshToken(sessionManager = authInterceptor.sessionManager,getRawRetrofit()))
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private fun getOkHttpRaw(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor { chain ->
                val resp = chain.proceed(chain.request())
                if (resp.code == 200) {
                    try {
                        val myJson = resp.peekBody(2048).string()
                        //println(myJson)
                    } catch (e: Exception) {
                        println("Error parse json from intercept..............")
                    }
                } else {
                    //println(resp)
                }
                resp
            }
        return okHttpClient
    }
}


