package com.example.rickandmorty.ui.fragments.location.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val locationRepository : LocationRepository
): ViewModel(){

    fun fetchLocation(id: Int): MutableLiveData<LocationModel> {
        return locationRepository.fetchLocationApiService(id)
    }
}