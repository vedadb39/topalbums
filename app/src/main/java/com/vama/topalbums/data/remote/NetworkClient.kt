package com.vama.topalbums.data.remote


import com.vama.topalbums.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createRestClient(
    api: Class<T>,
    baseUrl: String = BuildConfig.BASE_URL,
): T {

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
    client.addInterceptor(loggingInterceptor)

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(api)
}
