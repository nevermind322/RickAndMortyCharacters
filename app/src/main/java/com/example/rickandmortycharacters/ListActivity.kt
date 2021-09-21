package com.example.rickandmortycharacters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.network.MyApiEndpoints
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class ListActivity : AppCompatActivity(),
    CharacterAdapter.OnCharacterClickListener {

    val viewModel: CharactersPageViewModel by lazy {
        ViewModelProvider(this).get(CharactersPageViewModel::class.java)
    }

    private val characters = mutableListOf<CharacterInfo>()
    private val adapter = CharacterAdapter(this)
    private var pageNumber = 1
    private val apiEndpoints: MyApiEndpoints by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        val recyclerView: RecyclerView = findViewById(R.id.list_characters)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.flow.collectLatest { adapter.submitData(it) }
        }
    }

    override fun onCharacterClick(characterInfo: CharacterInfo) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(CharacterInfo::class.simpleName, characterInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}
