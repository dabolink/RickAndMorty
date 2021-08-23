package com.dabolink.rickandmorty.models

import com.dabolink.rickandmorty.ui.main.TextItem
import org.json.JSONArray
import org.json.JSONObject

data class Episode(val json: JSONObject) : TextItem {
    override val id: Int = json.getInt("id")
    val name: String = json.getString("name")
    val airDate: String = json.getString("air_date")
    val episode: String = json.getString("episode")
    private val charactersJSON: JSONArray = json.getJSONArray("characters")
    val characters: Array<String> = Array(charactersJSON.length()) {
        charactersJSON.getString(it)
    }
    val url: String = json.getString("url")
    val created: String = json.getString("created")

    override fun getText(): String {
        return "$id $name"
    }

    override fun toString(): String {
        return name
    }
}