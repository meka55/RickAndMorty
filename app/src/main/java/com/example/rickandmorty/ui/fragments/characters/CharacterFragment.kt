package com.example.rickandmorty.ui.fragments.characters

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.telecom.ConnectionService
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import java.sql.Connection

class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter = CharacterAdapter()

    override fun initialize() {
        binding.recyclerCharacter.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupObserves() {
        if (isOnline(requireContext())) {
            viewModel.fetchCharacter().observe(viewLifecycleOwner) {
                characterAdapter.submitList(it.results)
            }
        } else {
            viewModel.getAllFromRoom().observe(viewLifecycleOwner) {
                characterAdapter.submitList(it)
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
