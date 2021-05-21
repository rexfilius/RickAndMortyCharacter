package com.github.rexfilius.rickandmortycharacter

import android.util.Log
import androidx.lifecycle.*
import com.github.rexfilius.rickandmortycharacter.api.Results
import com.github.rexfilius.rickandmortycharacter.api.CharacterRepository
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _characterLiveData = MutableLiveData<List<Results>>()
    val characterLiveData: LiveData<List<Results>>
        get() = _characterLiveData

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _characterLiveData.value = repository.getCharacters().characters
                Log.d(TAG, "${_characterLiveData.value}")
            } catch (e: Exception) {
                Log.d(TAG, e.stackTraceToString())
            }
        }
    }

}

class MainViewModelFactory(private val repository: CharacterRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }
}