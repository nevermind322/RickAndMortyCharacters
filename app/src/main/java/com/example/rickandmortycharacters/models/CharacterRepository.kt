package com.example.rickandmortycharacters.models

import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.network.NetworkLayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository {

    suspend fun getCharacterById(id: Int): CharacterInfo? {

        val request = NetworkLayer.apiClient.getCharacterById(id)

        if (!request.isSuccessful) return null

        return request.body()
    }

    suspend fun getPageById(id : Int) : CharacterPage? {

        val request = NetworkLayer.apiClient.getPageById(id)

        if (!request.isSuccessful) return null

        return  request.body()
    }

    suspend fun getPageCount() : Int?{

        return getPageById(1)?.info?.pages

    }

    suspend fun GetPages(lastPageId : Int) : Flow<CharacterPage?> {

        return flow {
            for (i in 1..lastPageId) {
                emit(getPageById(i))
            }

        }
    }
}
