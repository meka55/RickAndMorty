package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.character.CharacterModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/character")
   suspend fun fetchCharacter(
        @Query("page") page: Int
    ): RickAndMortyResponse<CharacterModel>

    @GET("api/character/{id}")
     fun fetchCharacterApiService(
        @Path("id") id: Int
    ) : Call<CharacterModel>

}