package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.episode.EpisodeModel
import retrofit2.Call
import retrofit2.http.GET

interface EpisodeApi {
    @GET("api/episode")
    fun fetchEpisode(): Call<RickAndMortyResponse<EpisodeModel>>
}