package com.galodb.data.network

import com.galodb.data.network.interceptor.ServerErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GTestClient(private val baseUrl: String) {

    companion object {
        const val TIMEOUT: Long = 50
        const val ACCEPT_HEADER = "Accept"
        const val ACCEPT_VALUE = "application/json"
    }

    val api: GTestApi

    init {
        val okHttpClient = createHttpClient()

        val logging = createLoggingInterceptor()
        val serverError = createServerInterceptor()

        okHttpClient.addInterceptor(logging)
        okHttpClient.addInterceptor(serverError)

        api = setUpRetrofit(okHttpClient).create(GTestApi::class.java)
    }

    private fun createHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(ACCEPT_HEADER, ACCEPT_VALUE)
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun createServerInterceptor() = ServerErrorInterceptor()

    private fun setUpRetrofit(okHttpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
