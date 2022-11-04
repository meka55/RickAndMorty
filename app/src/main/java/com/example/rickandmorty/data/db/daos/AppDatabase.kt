package com.example.rickandmorty.data.db.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.db.converter.Converter
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel

@Database(
    entities = [CharacterModel::class, EpisodeModel::class, LocationModel::class],
    version = 1
)
@TypeConverters(Converter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao

    abstract fun locationDao(): LocationDao
}
