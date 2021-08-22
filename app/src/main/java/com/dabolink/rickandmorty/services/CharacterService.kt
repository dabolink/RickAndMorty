package com.dabolink.rickandmorty.services

import androidx.lifecycle.MutableLiveData
import com.dabolink.rickandmorty.models.Character
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CharacterService {
    private val apiCLI = OkHttpClient()
    fun getAllCharacters(): MutableLiveData<Array<Character>> {
        val charactersLD: MutableLiveData<Array<Character>> = MutableLiveData()
        val request = Request.Builder().url("${ServiceINFO.BASE_API}/character").build()
        apiCLI.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    val charactersJSON: JSONArray = JSONArray(response.body()?.string())
                    val characters: Array<Character> = Array(charactersJSON.length()) {
                        Character(charactersJSON.getJSONObject(it))
                    }
                    charactersLD.value = characters
                }

            }
        })
        return charactersLD
    }
}