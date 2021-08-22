package com.dabolink.rickandmorty.models

import org.json.JSONArray
import org.json.JSONObject

data class Location(val json: JSONObject) {
    val id: Int = json.getInt("id")
    val name: String = json.getString("name")
    val type: String = json.getString("type")
    val dimension: String = json.getString("dimension")
    private val residentsJSON: JSONArray = json.getJSONArray("residents")
    val residents: Array<String> = Array(residentsJSON.length()){
        residentsJSON.getString(it)
    }
    var created: String = json.getString("created")

    override fun toString(): String {
        return name
    }
}