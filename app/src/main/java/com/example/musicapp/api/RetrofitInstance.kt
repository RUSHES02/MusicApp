package com.example.musicapp.api

import com.example.musicapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://spotify23.p.rapidapi.com"
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
                .addHeader("x-rapidapi-host", BuildConfig.API_HOST)
                .build()
            chain.proceed(request)
        }
        .build()

    val api: SongApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SongApi::class.java)
    }
}