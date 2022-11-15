package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {

    @GET("api/episode")
    suspend fun fetchEpisode(
        @Query("page") page: Int
    ): RickAndMortyResponse<EpisodeModel>

    @GET("api/episode/{id}")
     fun fetchEpisodeApiService(
        @Path("id") id: Int
    ) : Call<EpisodeModel>
}