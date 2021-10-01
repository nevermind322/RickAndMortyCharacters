package com.example.rickandmortycharacters.utils.extensions

import com.example.rickandmortycharacters.models.CharacterPage

fun CharacterPage.Info.getNextPageFromNext(): Int? {
    if (this.next != null) return this.next.split("?page=")[1].toInt()
    return null
}