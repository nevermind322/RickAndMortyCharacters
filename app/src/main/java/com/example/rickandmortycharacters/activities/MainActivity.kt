package com.example.rickandmortycharacters.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}


