package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApi {

    @GET("api/location")
    suspend fun fetchLocation(
        @Query("page") page: Int
    ): RickAndMortyResponse<LocationModel>

    @GET("api/location/{id}")
     fun fetchLocationApiService(
        @Path("id") id: Int
    ) : Call<LocationModel>
}