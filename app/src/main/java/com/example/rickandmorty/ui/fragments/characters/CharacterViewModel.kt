package com.example.rickandmorty.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.character.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository:CharacterRepository

) : ViewModel() {

    fun fetchCharacter(): LiveData<PagingData<CharacterModel>> {
        return repository.fetchCharacter().cachedIn(viewModelScope)
    }
}