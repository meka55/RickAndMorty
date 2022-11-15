package com.example.rickandmorty.ui.fragments.location

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.ui.adapters.LocationAdapter
import com.example.rickandmorty.ui.adapters.OnClick
import com.example.rickandmorty.ui.fragments.characters.CharacterViewModel
import com.example.rickandmorty.ui.fragments.episodes.EpisodeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<FragmentLocationBinding, LocationViewModel>(R.layout.fragment_location), OnClick {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val  locationAdapter = LocationAdapter(this)

    override fun initialize() {
        binding.recyclerLocation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupObserves() {
        if (isOnline(requireContext())) {
            viewModel.fetchLocation().observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    locationAdapter.submitData(it)
                }
            }
        }
    }

    override fun listener(id: Int?) {
        id?.let {
            findNavController().navigate(
                LocationFragmentDirections
                    .actionLocationFragmentToLocationDetailFragment(it)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Toast.makeText(context, "NetworkCapabilities.TRANSPORT_CELLULAR", Toast.LENGTH_SHORT).show()
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Toast.makeText(context, "NetworkCapabilities.TRANSPORT_WIFI", Toast.LENGTH_SHORT).show()
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Toast.makeText(context, "NetworkCapabilities.TRANSPORT_ETHERNET", Toast.LENGTH_SHORT).show()
                return true
            }
        }
    }
    return false
}