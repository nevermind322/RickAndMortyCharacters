package com.example.rickandmortycharacters.DI

import androidx.paging.PagingConfig
import com.example.rickandmortycharacters.adapters.CharacterAdapterComparator
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
        viewModel { CharactersPageViewModel(get(), get()) }
        single {CharacterPageDataSource(get())}

    }

    val pagingModule = module {
        single { PagingConfig(pageSize = 20, prefetchDistance = 5) }
        single {CharacterAdapterComparator()}
    }
}