package com.example.rickandmortycharacters.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycharacters.getNextPageFromNext
import com.example.rickandmortycharacters.network.ApiClient
import org.koin.java.KoinJavaComponent.inject
import java.lang.Exception


class CharacterPageDataSource : PagingSource<Int, CharacterInfo>() {

    private val apiService : ApiClient by inject(ApiClient::class.java)

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

