package com.example.rickandmorty.ui.fragments.episodes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.ui.adapters.EpisodeAdapter
import com.example.rickandmorty.ui.fragments.characters.CharacterViewModel

class EpisodeFragment :
    BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter()

    override fun initialize() {
        binding.recyclerEpisode.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupObserves() {
        if (isOnline(requireContext())) {
            viewModel.fetchEpisode().observe(viewLifecycleOwner) {
                episodeAdapter.submitList(it.results)
            }
        } else {
            viewModel.getAllFromRoom().observe(viewLifecycleOwner) {
                episodeAdapter.submitList(it)
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
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    Toast.makeText(requireContext(), "NetworkCapabilities.TRANSPORT_CELLULAR", Toast.LENGTH_LONG).show()
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    Toast.makeText(requireContext(), "NetworkCapabilities.TRANSPORT_WIFI", Toast.LENGTH_LONG).show()
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    Toast.makeText(requireContext(), "NetworkCapabilities.TRANSPORT_ETHERNET", Toast.LENGTH_LONG).show()
                    return true
                }
            }
        }
        return false
    }
}
