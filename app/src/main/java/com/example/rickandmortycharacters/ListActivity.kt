package com.example.rickandmortycharacters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity(), CharacterAdapter.OnCharacterClickListener {

    private val characters = mutableListOf<CharacterInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setInitialData()

        val recyclerView: RecyclerView = findViewById(R.id.list_characters)

        val adapter = CharacterAdapter(this)
        adapter.setData(characters)
        recyclerView.adapter = adapter
    }

    private fun setInitialData() {

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
                R.drawable.long_image
            )
        )

    }

    override fun onCharacterClick(characterInfo: CharacterInfo) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(CharacterInfo::class.simpleName, characterInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}