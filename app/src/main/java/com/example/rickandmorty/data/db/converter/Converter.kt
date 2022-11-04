package com.example.rickandmorty.data.db.converter

import androidx.room.TypeConverter
import com.example.rickandmorty.models.character.Location
import com.example.rickandmorty.models.character.Origin

class Converter {

    @TypeConverter
    fun fromLocation(model: Location): String {
        return model.name
    }

    @TypeConverter
    fun tvLocation(name: String): Location {
        return Location(name, name)
    }

    @TypeConverter
    fun fromOrigin(model: Origin): String {
        return model.name
    }

    @TypeConverter
    fun tvOrigin(name: String): Origin {
        return Origin(name,name)
    }
}