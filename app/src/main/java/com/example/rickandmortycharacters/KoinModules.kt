package com.example.rickandmortycharacters

import com.example.rickandmortycharacters.network.NetworkLayer
import org.koin.dsl.module

object KoinModules {

    val netModule = module {
        single { NetworkLayer.apiClient }
    }
}