package com.example.rickandmorty

import android.app.Application
import androidx.room.Room
import com.example.rickandmorty.data.RetrofitClient
import com.example.rickandmorty.data.db.daos.AppDatabase
import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.data.network.apiservices.EpisodeApi
import com.example.rickandmorty.data.network.apiservices.LocationApi

class App : Application() {
    companion object{
        private val retrofitClient = RetrofitClient()
        var characterApi: CharacterApi? = null
        var episodeApi: EpisodeApi? = null
        var locationApi: LocationApi? = null
        var appDatabase: AppDatabase? = null
    }
    private fun instanceOfRoom():AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
        }
        return appDatabase
    }

    override fun onCreate() {
        super.onCreate()
        characterApi = retrofitClient.provideCharacterApiService()
        episodeApi = retrofitClient.provideEpisodeApiService()
        locationApi = retrofitClient.provideLocationApiService()
        instanceOfRoom()
    }
}