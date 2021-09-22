package com.example.rickandmortycharacters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycharacters.adapters.CharacterAdapter
import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.viewmodels.CharactersPageViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity(),
    CharacterAdapter.OnCharacterClickListener {

    private val listViewModel: CharactersPageViewModel by viewModel()

    private val adapter = CharacterAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView: RecyclerView = findViewById(R.id.list_characters)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            listViewModel.flow.collectLatest { adapter.submitData(it) }
        }
        adapter.addLoadStateListener {
            if (it.source.refresh is LoadState.Error){
                Toast.makeText(this@ListActivity, "Network error", Toast.LENGTH_LONG).show()
            }

            if (it.source.append is LoadState.Error){
                Toast.makeText(this@ListActivity, "Network error", Toast.LENGTH_LONG).show()
            }

            if (it.source.prepend is LoadState.Error){
                Toast.makeText(this@ListActivity, "Network error", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCharacterClick(characterInfo: CharacterInfo) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(CharacterInfo::class.simpleName, characterInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}
