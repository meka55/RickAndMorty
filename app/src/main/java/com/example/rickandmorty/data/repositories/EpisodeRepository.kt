package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.network.apiservices.EpisodeApi
import com.example.rickandmorty.data.repositories.pagingsources.EpisodePagingSource
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeApi: EpisodeApi
) {

    fun fetchEpisode(): LiveData<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(episodeApi)
            }, initialKey = 1
        ).liveData
    }
    val data: MutableLiveData<EpisodeModel> = MutableLiveData()

     fun fetchEpisodeApiService(id: Int): MutableLiveData<EpisodeModel> {
        episodeApi.fetchEpisodeApiService(id)
            .enqueue(object : Callback<EpisodeModel> {
                override fun onResponse(
                    call: Call<EpisodeModel>,
                    response: Response<EpisodeModel>
                ) {
                    response.body()?.let {
                        data.value = it
                    }
                }

                override fun onFailure(
                    call: Call<EpisodeModel>,
                    t: Throwable
                ) {

                }
            })
        return data
    }

}