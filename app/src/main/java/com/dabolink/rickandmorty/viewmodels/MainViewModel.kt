package com.dabolink.rickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dabolink.rickandmorty.models.Character
import com.dabolink.rickandmorty.models.Episode
import com.dabolink.rickandmorty.models.Location
import com.dabolink.rickandmorty.services.RAMService

class MainViewModel : ViewModel() {
    private val ramService: RAMService = RAMService()

    val characters: LiveData<ArrayList<Character>> = ramService.charactersLD.also {
        ramService.loadCharacters()
    }

    val locations: LiveData<ArrayList<Location>> = ramService.locationsLD.also {
        ramService.loadLocations()
    }

    val episodes: LiveData<ArrayList<Episode>> = ramService.episodesLD.also {
        loadEpisodes()
    }

    fun loadCharacters() {
        ramService.loadCharacters()
    }

    fun loadLocations() {
        ramService.loadLocations()
    }

    fun loadEpisodes() {
        ramService.loadEpisodes()
    }

    fun reloadCharacters() {
        ramService.reloadCharacters()
    }

}