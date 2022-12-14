package com.example.rickandmorty.models.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.base.IBaseDiffUtil
import com.google.gson.annotations.SerializedName

data class LocationModel(

    @SerializedName("id")
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("dimension")
    val dimension: String
):IBaseDiffUtil
