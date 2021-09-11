package com.example.rickandmortycharacters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterInfo(
    val name: String,
    var status: String,
    val species: String,
    val type: String,
    val sex: String,
    val imageResource: String
): Parcelable