package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.character.CharacterModel
import retrofit2.Call
import retrofit2.http.GET

interface CharacterApi {
    @GET("api/character")
    fun fetchCharacter(): Call<RickAndMortyResponse<CharacterModel>>
}