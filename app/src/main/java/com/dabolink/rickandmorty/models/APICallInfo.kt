package com.dabolink.rickandmorty.models

import org.json.JSONObject

data class APICallInfo(val json: JSONObject) {
    val count: Int = 0
    val pages: Int = 0
    val next: String = ""
    val prev: String = ""
}
