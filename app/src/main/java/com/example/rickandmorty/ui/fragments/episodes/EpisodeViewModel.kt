package com.example.rickandmorty.ui.fragments.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.models.episode.EpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
): ViewModel() {

    fun fetchEpisode(): LiveData<PagingData<EpisodeModel>> {
        return repository.fetchEpisode().cachedIn(viewModelScope)
    }
}