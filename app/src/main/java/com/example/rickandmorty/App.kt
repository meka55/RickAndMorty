package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.data.network.apiservices.EpisodeApi
import com.example.rickandmorty.data.network.apiservices.LocationApi

class App : Application() {
    companion object{
        private val retrofitClient = RetrofitClient()
        var characterApi: CharacterApi? = null
        var episodeApi: EpisodeApi? = null
        var locationApi: LocationApi? = null
    }

    override fun onCreate() {
        super.onCreate()
        characterApi = retrofitClient.provideCharacterApiService()
        episodeApi = retrofitClient.provideEpisodeApiService()
        locationApi = retrofitClient.provideLocationApiService()
    }
}