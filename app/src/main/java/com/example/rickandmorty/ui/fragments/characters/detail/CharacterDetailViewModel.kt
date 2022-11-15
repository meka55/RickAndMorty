package com.example.rickandmorty.ui.fragments.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.character.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository:CharacterRepository
) : ViewModel(){

     fun fetchCharacter(id: Int): MutableLiveData<CharacterModel> {
        return characterRepository.fetchCharacterApiService(id)
    }
}