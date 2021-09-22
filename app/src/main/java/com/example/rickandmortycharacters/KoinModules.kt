package com.example.rickandmortycharacters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycharacters.models.CharacterPageDataSource
import com.example.rickandmortycharacters.network.NetworkLayer
import com.example.rickandmortycharacters.viewmodels.CharactersPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {

    val netModule = module {
        single { NetworkLayer.apiClient }
    }

    val MvvmModule = module {
        viewModel { CharactersPageViewModel() }
    }

    val pagingModule = module {
        single { PagingConfig(pageSize = 20, prefetchDistance = 5) }
    }
}