package com.example.rickandmorty.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.RickAndMortyResponse
import com.example.rickandmorty.models.character.CharacterModel

class CharacterViewModel : ViewModel() {

    private val repository = CharacterRepository()

    fun fetchCharacter() : MutableLiveData<RickAndMortyResponse<CharacterModel>>{
        return repository.fetchCharacter()
    }

    fun getAllFromRoom(): LiveData<List<CharacterModel>>{
        return repository.getCharacters()
    }
}