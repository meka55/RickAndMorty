package com.example.rickandmorty.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.models.episode.EpisodeModel

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episode")
    fun getAllList(): LiveData<List<EpisodeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<EpisodeModel>)
}