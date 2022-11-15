package com.example.rickandmorty.ui.fragments.episodes.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.ui.fragments.characters.detail.CharacterDetailFragmentArgs
import com.example.rickandmorty.ui.fragments.characters.detail.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<FragmentEpisodeDetailBinding, EpisodeDetailViewModel>(R.layout.fragment_episode_detail) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel: EpisodeDetailViewModel by viewModels()
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun setupObserves() = with(binding) {
        viewModel.fetchEpisode(args.id).observe(viewLifecycleOwner) { model ->
            binding.txtIdDetail.text = model.id.toString()
            binding.txtNameEpisodeDetail.text = model.name
            binding.txtAirDateEpisodeDetail.text = model.air_date
            binding.txtEpisodeEpisodeDetail.text =model.episode
        }
    }
}