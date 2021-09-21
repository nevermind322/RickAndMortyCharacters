package com.example.rickandmortycharacters.network

import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.models.CharacterPage
import retrofit2.Response

class ApiClient(
    private val apiEndpoints: MyApiEndpoints
) {
    suspend fun getCharacterById(Id : Int) : Response<CharacterInfo> = apiEndpoints.getCharacterById(Id)

    suspend fun getPageById(Id: Int) : Response<CharacterPage> = apiEndpoints.getPageById(Id)

}