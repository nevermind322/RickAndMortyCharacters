package com.example.rickandmortycharacters

import android.app.Application
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var characterApi: MyApiEndpoints

    override fun onCreate() {
        super.onCreate()
        configureRetrofit()
    }

    private fun configureRetrofit() {

        val gson = GsonBuilder()
            .registerTypeAdapter(CharacterInfo::class.java, CharacterDeserializer())
            .registerTypeAdapter(CharacterPage::class.java, PageDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        characterApi = retrofit.create(MyApiEndpoints::class.java)
    }
}
