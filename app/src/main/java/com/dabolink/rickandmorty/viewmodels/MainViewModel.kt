package com.dabolink.rickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dabolink.rickandmorty.models.APICallInfo
import com.dabolink.rickandmorty.models.Character
import com.dabolink.rickandmorty.models.Episode
import com.dabolink.rickandmorty.models.Location
import com.dabolink.rickandmorty.services.RAMService

class MainViewModel : ViewModel() {
    private val ramService: RAMService = RAMService()

    private lateinit var charactersInfo: APICallInfo
    val characters: LiveData<List<Character>> = ramService.getAllCharacters()

    private lateinit var locationsInfo: APICallInfo
    val locations: LiveData<List<Location>> = ramService.getAllLocations()

    private lateinit var episodesInfo: APICallInfo
    val episodes: LiveData<List<Episode>> = ramService.getAllEpisodes()


}