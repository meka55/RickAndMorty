package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.location.LocationModel
import retrofit2.Call
import retrofit2.http.GET

interface LocationApi {

    @GET("api/location")
    fun fetchLocation(): Call<RickAndMortyResponse<LocationModel>>
}