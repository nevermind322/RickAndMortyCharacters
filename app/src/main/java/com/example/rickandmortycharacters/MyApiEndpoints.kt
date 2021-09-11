package com.example.rickandmortycharacters

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiEndpoints {

    @GET("character/{id}")
    fun getCharacter(@Path("id") characterId: Int) : Call<CharacterInfo>

    @GET("character")
    fun getPage(@Query("page") pageValue : Int) : Call<CharacterPage>


}