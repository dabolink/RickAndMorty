package com.dabolink.rickandmorty.models

import com.dabolink.rickandmorty.ui.main.TextItem
import org.json.JSONArray
import org.json.JSONObject

data class Location(private val json: JSONObject) : TextItem{
    override val id: Int = json.getInt("id")
    val name: String = json.getString("name")
    val type: String = json.getString("type")
    val dimension: String = json.getString("dimension")
    private val residentsJSON: JSONArray = json.getJSONArray("residents")
    val residents: List<String> = List(residentsJSON.length()){
        residentsJSON.getString(it)
    }
    var url: String = json.getString("url")
    var created: String = json.getString("created")

    override fun getText(): String {
        return "[$dimension] $name"
    }

    override fun toString(): String {
        return name
    }
}