package com.example.rickandmortycharacters.viewmodels

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
import org.koin.java.KoinJavaComponent.inject

class CharactersPageViewModel : ViewModel() {

    private val config: PagingConfig by inject(PagingConfig::class.java)
    val flow: Flow<PagingData<CharacterInfo>> =
        Pager(config) { CharacterPageDataSource() }.flow.cachedIn(viewModelScope)
}
