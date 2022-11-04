package com.example.rickandmorty.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.episode.EpisodeModel
import com.example.rickandmorty.models.location.LocationModel

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getAllList(): LiveData<List<LocationModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list : List<LocationModel>)
}