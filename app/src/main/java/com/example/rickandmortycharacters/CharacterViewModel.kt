package com.example.rickandmortycharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.models.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val _characterInfoLiveData = MutableLiveData<CharacterInfo?>()
    val characterInfoLiveData: LiveData<CharacterInfo?> = _characterInfoLiveData

    fun postCharacter(id : Int){

        viewModelScope.launch{
            val response = repository.getCharacterById(id)

            _characterInfoLiveData.postValue(response)

        }


    }
}