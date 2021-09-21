package com.example.rickandmortycharacters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortycharacters.models.CharacterInfo

class CharacterAdapter(private val listener: OnCharacterClickListener) :
    PagingDataAdapter<CharacterInfo, CharacterAdapter.CharacterViewHolder>(comparatop){

    private val characterList = mutableListOf<CharacterInfo>()

    class CharacterViewHolder(itemView: View, private val listener: OnCharacterClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.list_character_image)
        private val name = itemView.findViewById<TextView>(R.id.list_character_name)

        fun bind(info: CharacterInfo) {
            name.text = info.name
            image.load(info.image)
            image.setOnClickListener { listener.onCharacterClick(info) }
        }
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(characterInfo: CharacterInfo)
    }

    object comparatop : DiffUtil.ItemCallback<CharacterInfo>(){
        override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
            return oldItem == newItem
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<CharacterInfo>) {
        characterList.clear()
        characterList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false),
            listener = listener
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character: CharacterInfo? = getItem(position)
        if(character!= null)
        holder.bind(character)
    }

}