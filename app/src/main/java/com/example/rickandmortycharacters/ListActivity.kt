package com.example.rickandmortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.rickandmortycharacters.viewmodels.CharactersPageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ListFragment>(R.id.fragment_container)
        }
    }
}


