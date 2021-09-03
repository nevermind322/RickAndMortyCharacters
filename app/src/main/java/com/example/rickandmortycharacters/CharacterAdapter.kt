package com.example.rickandmortycharacters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private val characterList: MutableList<CharacterInfo> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.list_character_image)
        private val name: TextView = itemView.findViewById(R.id.list_character_name)
        fun bind(info: CharacterInfo) {
            name.text = info.name
            image.setImageResource(info.imageResource)

        }
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(characterInfo: CharacterInfo, position: Int)
    }

    private var onCharacterClickListener: OnCharacterClickListener? = null
    fun setClickListener(clickListener: OnCharacterClickListener){
        onCharacterClickListener = clickListener
    }
    fun setData(newData: MutableList<CharacterInfo>) {
        characterList.clear()
        characterList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character: CharacterInfo = characterList[position]
        holder.bind(character)
        holder.itemView.setOnClickListener {
            onCharacterClickListener?.onCharacterClick(
                character,
                position
            )
        }
    }
    override fun getItemCount(): Int = characterList.size
}