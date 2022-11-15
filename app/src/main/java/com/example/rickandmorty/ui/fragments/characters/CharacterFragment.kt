package com.example.rickandmorty.ui.fragments.characters

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
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.ui.adapters.OnClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character),
    OnClick {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this)

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
                lifecycleScope.launch {
                    characterAdapter.submitData(it)
                }
            }
        }
    }

    override fun listener(id: Int?) {
        id?.let {
            findNavController().navigate(
                CharacterFragmentDirections
                    .actionCharacterFragmentToCharacterDetailFragment(it)
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
                Toast.makeText(context, "NetworkCapabilities.TRANSPORT_WIFI", Toast.LENGTH_SHORT)
                    .show()
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
