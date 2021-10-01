package com.example.rickandmortycharacters.models

data class CharacterPage(
    val pageInfo: PageInfo = PageInfo(),
    val results: List<CharacterInfo> = emptyList()
)

data class PageInfo(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null
)

