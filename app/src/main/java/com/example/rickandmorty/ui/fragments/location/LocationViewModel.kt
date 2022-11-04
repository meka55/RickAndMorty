package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationViewModel : ViewModel() {

    private val repository = LocationRepository()

    fun fetchLocation(): MutableLiveData<RickAndMortyResponse<LocationModel>> {
        return repository.fetchLocation()
    }

    fun getAllFromRoom(): LiveData<List<LocationModel>> {
        return repository.getEpisode()
    }
}