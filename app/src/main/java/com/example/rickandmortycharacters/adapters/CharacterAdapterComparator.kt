package com.example.rickandmortycharacters.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortycharacters.models.CharacterInfo

class CharacterAdapterComparator : DiffUtil.ItemCallback<CharacterInfo>() {
    override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
        return oldItem == newItem
    }
}