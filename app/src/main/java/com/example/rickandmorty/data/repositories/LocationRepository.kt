package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.network.apiservices.LocationApi
import com.example.rickandmorty.data.repositories.pagingsources.LocationPagingSource
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApi: LocationApi
){

    fun fetchLocation(): LiveData<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                LocationPagingSource(locationApi)
            }, initialKey = 1
        ).liveData
    }
    val data: MutableLiveData<LocationModel> = MutableLiveData()

    fun fetchLocationApiService(id: Int): MutableLiveData<LocationModel> {
       locationApi.fetchLocationApiService(id)
            .enqueue(object : Callback<LocationModel> {
                override fun onResponse(
                    call: Call<LocationModel>,
                    response: Response<LocationModel>
                ) {
                    response.body()?.let {
                        data.value = it
                    }
                }

                override fun onFailure(
                    call: Call<LocationModel>,
                    t: Throwable
                ) {

                }
            })
        return data
    }

}