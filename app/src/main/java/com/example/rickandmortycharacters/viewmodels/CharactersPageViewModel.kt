package com.example.rickandmortycharacters.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.models.CharacterPageDataSource
import kotlinx.coroutines.flow.Flow

class CharactersPageViewModel(
    config: PagingConfig,
    characterPageDataSourceFactory: CharacterPageDataSource
) : ViewModel() {

    var clickedCharacter : CharacterInfo? = null

    val flow: Flow<PagingData<CharacterInfo>> =
        Pager(config) {characterPageDataSourceFactory} .flow.cachedIn(viewModelScope)
}
