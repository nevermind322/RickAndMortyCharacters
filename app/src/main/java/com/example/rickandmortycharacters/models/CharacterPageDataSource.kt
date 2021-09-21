package com.example.rickandmortycharacters.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycharacters.network.MyApiEndpoints
import com.example.rickandmortycharacters.network.NetworkLayer
import org.koin.java.KoinJavaComponent.inject
import java.lang.Exception


class CharacterPageDataSource : PagingSource<Int, CharacterInfo>() {

    val apiService = NetworkLayer.apiClient


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterInfo> {

        return try {

            val nextPage = params.key ?: 1
            val response = apiService.getPageById(nextPage).body()

            LoadResult.Page(
                data = response!!.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.info.getNextPageFromNext()
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CharacterInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

    fun CharacterPage.Info.getNextPageFromNext(): Int? {
        if (this.next != null) return this.next.split("?page=")[1].toInt()
        return null

    }