package com.example.rickandmorty.ui.fragments.episodes.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val episodeRepository:EpisodeRepository
): ViewModel(){

    fun fetchEpisode(id: Int): MutableLiveData<EpisodeModel> {
        return episodeRepository.fetchEpisodeApiService(id)
    }
}