package com.example.rickandmorty.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback<B: IBaseDiffUtil> : DiffUtil.ItemCallback<B>() {

    override fun areItemsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem == newItem
    }
}