package com.dabolink.rickandmorty.models

import org.json.JSONObject

data class APICallInfo(private val json: JSONObject) {
    val count: Int = json.getInt("count")
    val pages: Int = json.getInt("pages")
    val next: String? = if (json.isNull("next")) null else json.getString("next")
    val prev: String? = if (json.isNull("prev")) null else json.getString("next")
}
