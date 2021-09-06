package com.example.rickandmortycharacters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private val listener: OnCharacterClickListener) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characterList = mutableListOf<CharacterInfo>()

    class CharacterViewHolder(itemView: View, private val listener: OnCharacterClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.list_character_image)
        private val name: TextView = itemView.findViewById(R.id.list_character_name)

        fun bind(info: CharacterInfo) {
            name.text = info.name
            image.setImageResource(info.imageResource)
            image.setOnClickListener { listener.onCharacterClick(info) }
        }
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(characterInfo: CharacterInfo)
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
        val character: CharacterInfo = characterList[position]
        holder.bind(character)

    }

    override fun getItemCount(): Int = characterList.size
}