package com.example.rickandmorty.ui.fragments.characters.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(R.layout.fragment_character_detail) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel: CharacterDetailViewModel by viewModels()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun setupObserves() = with(binding) {
            viewModel.fetchCharacter(args.id).observe(viewLifecycleOwner) { model ->
            binding.imageCharacterDetail.load(model.image)
            binding.txtIdCharacterDetail.text = model.id.toString()
            txtStatusCharacterDetail.text = model.status
            txtNameCharacterDetail.text = model.name
            txtSpeciesCharacterDetail.text = model.species
                if (txtStatusCharacterDetail.text == "Alive") {
                    viewStatus.setBackgroundResource(R.drawable.alive_status)
                } else {
                    viewStatus.setBackgroundResource(R.drawable.dead_status)
                }
        }
    }
}