package com.example.rickandmortycharacters.utils.extensions

import com.example.rickandmortycharacters.models.PageInfo

fun PageInfo.getNextPageFromNext(): Int? {
    if (this.next != null) return this.next.split("?page=")[1].toInt()
    return null
}