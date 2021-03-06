package com.dabolink.rickandmorty.models

import com.dabolink.rickandmorty.ui.main.TextItem
import org.json.JSONArray
import org.json.JSONObject

data class Character(private val json: JSONObject) : TextItem {
    override val id: Int = json.getInt("id")
    val name: String = json.getString("name")
    val status: String = json.getString("status")
    val species: String = json.getString("species")
    val type: String = json.getString("type")
    val gender: String = json.getString("gender")
    val origin: LocationRef = LocationRef(json.getJSONObject("origin"))
    val location: LocationRef = LocationRef(json.getJSONObject("location"))
    val image: String = json.getString("image")
    private val episodeJSON: JSONArray = json.getJSONArray("episode")
    val episodes: List<String> = List(episodeJSON.length()) {
        episodeJSON.getString(it)
    }
    val url: String = json.getString("url")
    val created_at: String = json.getString("created")

    override fun getText(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}