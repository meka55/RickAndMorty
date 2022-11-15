package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.base.BaseDiffUtilItemCallback
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.models.episode.EpisodeModel

class EpisodeAdapter(
    private val onClick: OnClick
    ):PagingDataAdapter<EpisodeModel, EpisodeAdapter.ViewHolder>(BaseDiffUtilItemCallback()) {

    class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: EpisodeModel?, onClick: OnClick) {
            binding.tvEpisodeName.text = item?.name
            binding.tvAirDate.text = item?.air_date
            binding.tvEpisodeCode.text = item?.episode
            itemView.setOnClickListener {
                onClick.listener(item?.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEpisodeBinding.inflate(
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
interface EpisodeOnClick {
    fun listener(id: Int?)
}
