package com.dabolink.rickandmorty.models

import org.json.JSONObject

data class LocationRef(private val jsonObject: JSONObject) {
    val name: String = jsonObject.getString("name")
    val url: String? = jsonObject.getString("url")
}
