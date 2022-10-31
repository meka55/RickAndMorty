package com.example.rickandmorty.data

import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.data.network.apiservices.EpisodeApi
import com.example.rickandmorty.data.network.apiservices.LocationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(providerInspector())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()

    private fun providerInspector(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharacterApiService(): CharacterApi {
        return retrofit.create(CharacterApi::class.java)
    }

    fun provideEpisodeApiService(): EpisodeApi {
        return retrofit.create(EpisodeApi::class.java)
    }

    fun provideLocationApiService(): LocationApi {
        return retrofit.create(LocationApi::class.java)
    }
}