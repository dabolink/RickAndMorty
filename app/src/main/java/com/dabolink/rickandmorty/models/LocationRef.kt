package com.dabolink.rickandmorty.models

import org.json.JSONObject

data class LocationRef(val jsonObject: JSONObject) {
    var name = ""
    var link = ""
}
