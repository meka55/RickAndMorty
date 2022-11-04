package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.App
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.episode.EpisodeModel
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
                    response.body()?.let {
                        App.appDatabase?.locationDao()?.insertList(it.results)
                        data.value = it
                    }
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

    fun getEpisode(): LiveData<List<LocationModel>> {
        return App.appDatabase?.locationDao()?.getAllList()!!
    }
}