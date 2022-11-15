package com.example.rickandmorty.ui.fragments.episodes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.adapters.EpisodeAdapter
import com.example.rickandmorty.ui.adapters.OnClick
import com.example.rickandmorty.ui.fragments.characters.CharacterFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode), OnClick {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter(this)

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
                lifecycleScope.launch {
                    episodeAdapter.submitData(it)
                }
            }
        }
    }

        override fun listener(id: Int?) {
            id?.let {
                findNavController().navigate(
                    EpisodeFragmentDirections
                        .actionEpisodeFragmentToEpisodeDetailFragment(it)
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
                    Toast.makeText(
                        context,
                        "NetworkCapabilities.TRANSPORT_CELLULAR",
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Toast.makeText(
                        context,
                        "NetworkCapabilities.TRANSPORT_WIFI",
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Toast.makeText(
                        context,
                        "NetworkCapabilities.TRANSPORT_ETHERNET",
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
            }
        }
        return false
    }