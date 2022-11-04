package com.example.rickandmorty.models.episode

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.base.IBaseDiffUtil
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episode")
data class EpisodeModel(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("air_date")
    val air_date: String,

    @SerializedName("episode")
    val episode: String
):IBaseDiffUtil
