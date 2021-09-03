package com.example.rickandmortycharacters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    val characters = mutableListOf<CharacterInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setInitialData()
        val recyclerView: RecyclerView = findViewById(R.id.list_characters)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = CharacterAdapter()
        val characterClickListener: CharacterAdapter.OnCharacterClickListener =
            object : CharacterAdapter.OnCharacterClickListener {
                override fun onCharacterClick(characterInfo: CharacterInfo, position: Int) {
                    val intent = Intent(this@ListActivity, DetailActivity::class.java)
                    intent.putExtra(CharacterInfo::class.simpleName, characterInfo)
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                }
            }
        adapter.setClickListener(characterClickListener)
        adapter.setData(characters)
        recyclerView.adapter = adapter

    }

    fun setInitialData() {

        characters.add(
            CharacterInfo(
                "Rick Sanchez",
                "Alive",
                "Human",
                "Scientist",
                "Male",
                R.drawable.another_rick
            )
        )
        characters.add(
            CharacterInfo(
                "Morty Smith",
                "Alive",
                "Human",
                "Student",
                "Male",
                R.drawable.morty_smith
            )
        )
        characters.add(
            CharacterInfo(
                "Beth Smith",
                "Alive",
                "Human",
                "Horse surgeon",
                "Female",
                R.drawable.beth_smith
            )
        )
        characters.add(
            CharacterInfo(
                "Summer Smith",
                "Alive",
                "Human",
                "Student",
                "Female",
                R.drawable.summer_smith
            )
        )
        characters.add(
            CharacterInfo(
                "Jerry Smith",
                "Alive",
                "Human",
                "unemployed",
                "Male",
                R.drawable.jerry_smith
            )
        )
        characters.add(
            CharacterInfo(
                "Birdperson",
                "Alive",
                "Bird Person",
                "Revolutionary, singer",
                "Male",
                R.drawable.bird_person
            )
        )
        characters.add(
            CharacterInfo(
                "Squanchy",
                "Unknown",
                "Squachy",
                "Musician",
                "Male",
                R.drawable.squanchy
            )
        )

    }
}