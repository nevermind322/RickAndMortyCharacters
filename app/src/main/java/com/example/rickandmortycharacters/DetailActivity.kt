package com.example.rickandmortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.rickandmortycharacters.models.CharacterInfo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val args: Bundle? = intent.extras
        val characterInfo: CharacterInfo? = args?.getParcelable(CharacterInfo::class.simpleName)

        if (characterInfo != null) {
            findViewById<TextView>(R.id.detail_name).text = characterInfo.name
            findViewById<ImageView>(R.id.detail_image).load(characterInfo.image)
            findViewById<TextView>(R.id.detail_species_value).text = characterInfo.species
            findViewById<TextView>(R.id.detail_status_value).text = characterInfo.status
            findViewById<TextView>(R.id.detail_type_value).text = characterInfo.type
            findViewById<TextView>(R.id.detail_sex_value).text = characterInfo.gender
        }
    }
}