package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.App
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.character.CharacterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    val data: MutableLiveData<RickAndMortyResponse<CharacterModel>> = MutableLiveData()

    fun fetchCharacter(): MutableLiveData<RickAndMortyResponse<CharacterModel>> {
        App.characterApi?.fetchCharacter()
            ?.enqueue(object : Callback<RickAndMortyResponse<CharacterModel>> {

                override fun onResponse(
                    call: Call<RickAndMortyResponse<CharacterModel>>,
                    response: Response<RickAndMortyResponse<CharacterModel>>
                ) {
                    response.body()?.let {
                        App.appDatabase?.characterDao()?.insertList(it.results)
                        data.value = it
                    }
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<CharacterModel>>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }

    fun getCharacters(): LiveData<List<CharacterModel>>{
        return App.appDatabase?.characterDao()?.getAllList()!!
    }
}