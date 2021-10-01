package com.example.rickandmortycharacters.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.fragments.ListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ListFragment>(R.id.fragment_container)
        }
    }
}


