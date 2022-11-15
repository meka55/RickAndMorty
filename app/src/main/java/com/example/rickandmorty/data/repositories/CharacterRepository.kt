package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.data.repositories.pagingsources.CharacterPagingSource
import com.example.rickandmorty.models.character.CharacterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val characterApi: CharacterApi
) {

    fun fetchCharacter(): LiveData<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                CharacterPagingSource(characterApi)
            }, initialKey = 1
        ).liveData
    }
    val data: MutableLiveData<CharacterModel> = MutableLiveData()

     fun fetchCharacterApiService(id: Int): MutableLiveData<CharacterModel> {
        characterApi.fetchCharacterApiService(id)
            .enqueue(object : Callback<CharacterModel>{
                override fun onResponse(
                    call: Call<CharacterModel>,
                    response: Response<CharacterModel>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return data
    }
}