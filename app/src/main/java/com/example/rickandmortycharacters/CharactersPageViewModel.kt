package com.example.rickandmortycharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.models.CharacterPage
import com.example.rickandmortycharacters.models.CharacterPageDataSource
import com.example.rickandmortycharacters.models.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharactersPageViewModel : ViewModel() {

    private val repo = CharacterRepository()

    /*private val _characterPageLiveData = MutableLiveData<CharacterPage?>()
    val characterPageLiveData: LiveData<CharacterPage?> = _characterPageLiveData

    fun loadPages() {
        viewModelScope.launch {
            val pagesCount = repo.getPageCount()
            if (pagesCount != null) {
                for (i in 1..pagesCount) {
                    _characterPageLiveData.postValue(repo.getPageById(i))
                }
            }
        }
*/
    val flow: Flow<PagingData<CharacterInfo>> = Pager(PagingConfig(20, prefetchDistance = 4)) {
        CharacterPageDataSource()
    }.flow.cachedIn(viewModelScope)
}
