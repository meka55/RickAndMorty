package com.example.rickandmorty.models.character

import androidx.room.PrimaryKey
import com.example.rickandmorty.base.IBaseDiffUtil
import com.google.gson.annotations.SerializedName

data class CharacterModel(

    @SerializedName("id")
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("location")
    val location: Location,

    @SerializedName("origin")
    val origin: Origin
):IBaseDiffUtil
