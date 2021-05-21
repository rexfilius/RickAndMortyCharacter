package com.github.rexfilius.rickandmortycharacter.characters

import android.util.Log
import androidx.lifecycle.*
import com.github.rexfilius.rickandmortycharacter.api.Results
import com.github.rexfilius.rickandmortycharacter.api.CharacterRepository
import kotlinx.coroutines.launch

const val TAG = "CharacterViewModel"

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _characterLiveData = MutableLiveData<List<Results>>()
    val characterLiveData: LiveData<List<Results>>
        get() = _characterLiveData

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _characterLiveData.value = characterRepository.getCharacters().results
                Log.d(TAG, "${_characterLiveData.value}")
            } catch (e: Exception) {
                Log.d(TAG, e.stackTraceToString())
            }
        }
    }

}

class CharacterViewModelFactory(private val characterRepository: CharacterRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(characterRepository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }
}