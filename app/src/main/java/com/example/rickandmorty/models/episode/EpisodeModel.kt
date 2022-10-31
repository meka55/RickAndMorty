package com.example.rickandmorty.models.episode

import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("air_date")
    val air_date :String,

    @SerializedName("episode")
    val episode :String
)
