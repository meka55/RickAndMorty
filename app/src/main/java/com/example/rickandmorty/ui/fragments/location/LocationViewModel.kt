package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository : LocationRepository

): ViewModel() {

    fun fetchLocation(): LiveData<PagingData<LocationModel>> {
        return repository.fetchLocation().cachedIn(viewModelScope)
    }
}