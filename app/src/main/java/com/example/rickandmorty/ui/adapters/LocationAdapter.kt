package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.base.BaseDiffUtilItemCallback
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.models.location.LocationModel

class LocationAdapter (
    private val onClick: OnClick
    ):PagingDataAdapter<LocationModel, LocationAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    class ViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root,) {
        fun onBind(item: LocationModel?,onClick: OnClick) {
            binding.tvLocationName.text = item?.name
            binding.tvLocationType.text = item?.type
            binding.tvDimension.text = item?.dimension
            itemView.setOnClickListener {
                onClick.listener(item?.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position),onClick)
    }
}

interface LocationOnClick {
    fun listener(id: Int?)
}