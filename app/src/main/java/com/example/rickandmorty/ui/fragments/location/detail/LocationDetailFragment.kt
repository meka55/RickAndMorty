package com.example.rickandmorty.ui.fragments.location.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.databinding.FragmentLocationDetailBinding
import com.example.rickandmorty.ui.fragments.episodes.detail.EpisodeDetailFragmentArgs
import com.example.rickandmorty.ui.fragments.episodes.detail.EpisodeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding, LocationDetailViewModel>(R.layout.fragment_location_detail) {

    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    override val viewModel: LocationDetailViewModel by viewModels()
    private val args by navArgs<LocationDetailFragmentArgs>()

    override fun setupObserves() = with(binding) {
        viewModel.fetchLocation(args.id).observe(viewLifecycleOwner) { model ->
            binding.txtIdLocationDetail.text = model.id.toString()
            binding.txtNameLocationDetail.text = model.name
            binding.txtTypeLocationDetail.text = model.type
            binding.txtDimensionLocationDetail.text = model.dimension
        }
    }
}

