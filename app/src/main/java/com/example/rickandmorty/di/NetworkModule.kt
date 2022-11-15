package com.example.rickandmorty.di

import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.data.network.apiservices.EpisodeApi
import com.example.rickandmorty.data.network.apiservices.LocationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    private var retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApi(): CharacterApi {
        return retrofitClient.provideCharacterApiService()
    }

    @Singleton
    @Provides
    fun provideEpisodeApi(): EpisodeApi {
        return retrofitClient.provideEpisodeApiService()
    }

    @Singleton
    @Provides
    fun provideLocationApi(): LocationApi {
        return retrofitClient.provideLocationApiService()
    }
}