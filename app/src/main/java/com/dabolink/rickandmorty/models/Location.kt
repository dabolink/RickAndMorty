package com.dabolink.rickandmorty.models

import org.json.JSONArray
import org.json.JSONObject

data class Location(val json: JSONObject) {
    val id: Int = 0
    val name: String = ""
    val type: String = ""
    val dimension: String = ""
    private val residentsJSON: JSONArray = json.getJSONArray("residents")
    val residents: Array<String> = Array(residentsJSON.length()){
        residentsJSON.getString(it)
    }
    var created: String = ""
}