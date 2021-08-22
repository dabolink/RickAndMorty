package com.dabolink.rickandmorty.models

import org.json.JSONArray
import org.json.JSONObject

data class Episode(val json: JSONObject) {
    val id: Int = 0
    val name: String = ""
    val airDate: String = ""
    val episode: String = ""
    private val charactersJSON: JSONArray = json.getJSONArray("")
    val characters: Array<String> = Array(charactersJSON.length()) {
        charactersJSON.getString(it)
    }
    val url: String = ""
    val created: String = ""
}