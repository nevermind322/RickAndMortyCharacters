package com.example.rickandmortycharacters

data class CharacterPage(val info: Info, val characterList: List<CharacterInfo>) {

    data class Info(val count : Int, val pages : Int, val next : String?)

}
