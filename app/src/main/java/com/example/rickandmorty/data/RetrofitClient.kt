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

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
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