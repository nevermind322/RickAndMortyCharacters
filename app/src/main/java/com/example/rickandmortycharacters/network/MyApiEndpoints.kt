package com.example.rickandmortycharacters.network

import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.models.CharacterPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiEndpoints {

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int) : Response<CharacterInfo>

    @GET("character")
    suspend fun getPageById(@Query("page") pageId : Int) : Response<CharacterPage>


}