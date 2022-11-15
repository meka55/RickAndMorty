package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseDiffUtilItemCallback
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.models.character.CharacterModel

class CharacterAdapter(
    private val onClick: OnClick
) : PagingDataAdapter<CharacterModel, CharacterAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CharacterModel?, onClick: OnClick) {
            binding.tvCharacter.text = item?.name
            binding.imCharacter.load(item?.image)
            binding.tvStatus.text = item?.status
            binding.tvSpecies.text = item?.species
            binding.tvLocation.text = item?.location?.name
            binding.tvFirstLocation.text = item?.origin?.name
            itemView.setOnClickListener {
                onClick.listener(item?.id)
            }


            when (item?.status) {
                "Alive" -> {
                    binding.imStatus.setImageResource(R.drawable.alive_status)
                }
                "Dead" -> {
                    binding.imStatus.setImageResource(R.drawable.dead_status)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), onClick)
    }
}

interface OnClick {
    fun listener(id: Int?)
}