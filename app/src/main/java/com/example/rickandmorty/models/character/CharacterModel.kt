package com.example.rickandmorty.models.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.base.IBaseDiffUtil
import com.google.gson.annotations.SerializedName

@Entity(tableName = "character")
data class CharacterModel(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
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
