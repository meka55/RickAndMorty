package com.example.rickandmorty.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.App
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.location.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationRepository {
    val data: MutableLiveData<RickAndMortyResponse<LocationModel>> = MutableLiveData()

    fun fetchLocation(): MutableLiveData<RickAndMortyResponse<LocationModel>> {
        App.locationApi?.fetchLocation()
            ?.enqueue(object : Callback<RickAndMortyResponse<LocationModel>> {
                override fun onResponse(
                    call: Call<RickAndMortyResponse<LocationModel>>,
                    response: Response<RickAndMortyResponse<LocationModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<LocationModel>>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }
}