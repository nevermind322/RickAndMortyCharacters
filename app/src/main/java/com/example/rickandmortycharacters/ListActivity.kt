package com.example.rickandmortycharacters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity(),
    CharacterAdapter.OnCharacterClickListener,
    Callback<CharacterPage> {

    private val characters = mutableListOf<CharacterInfo>()
    private val adapter = CharacterAdapter(this)
    private var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView: RecyclerView = findViewById(R.id.list_characters)

        val apiEndpoints: MyApiEndpoints = (this.application as App).characterApi


        val call = apiEndpoints.getPage(pageNumber)
        call.enqueue(this)

        recyclerView.adapter = adapter

    }


    override fun onCharacterClick(characterInfo: CharacterInfo) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(CharacterInfo::class.simpleName, characterInfo)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    override fun onResponse(call: Call<CharacterPage>?, response: Response<CharacterPage>?) {

        val page = response?.body()
        val charactersFromPage = page?.characterList
        pageNumber++

        if (charactersFromPage != null) characters.addAll(charactersFromPage)
        adapter.setData(characters)

        if (page?.info?.next != null) (this.application as App).characterApi.getPage(pageNumber).enqueue(this)

    }

    override fun onFailure(call: Call<CharacterPage>?, t: Throwable?) {}

}
