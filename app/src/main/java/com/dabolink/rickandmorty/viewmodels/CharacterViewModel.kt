package com.dabolink.rickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dabolink.rickandmorty.services.RAMService

class CharacterViewModel(id: Int) : ViewModel() {
    private val ramService: RAMService = RAMService()
    val character = ramService.loadCharacter(id)

    class Factory(id: Int): ViewModelProvider.Factory {
        val mID: Int = id
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(mID) as T
        }
    }
}