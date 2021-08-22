package com.dabolink.rickandmorty.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dabolink.rickandmorty.models.Character
import com.dabolink.rickandmorty.models.Episode
import com.dabolink.rickandmorty.models.Location
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class RAMService {
    private val apiCLI = OkHttpClient()
    fun getAllCharacters(): LiveData<Array<Character>> {

        val charactersLD: MutableLiveData<Array<Character>> = MutableLiveData()
        makeRequest("/character", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val charactersRespJSON = JSONObject(resp)
                        val charactersJSON = charactersRespJSON.getJSONArray("results")
                        //val apiINFO = APICallInfo(charactersJSON.getJSONObject("info"))
                        val characters: Array<Character> = Array(charactersJSON.length()) {
                            Character(charactersJSON.getJSONObject(it))
                        }
                        charactersLD.postValue(characters)
                    }
                }
            }

        })
        return charactersLD
    }

    fun getAllLocations(): LiveData<Array<Location>> {
        val locationsLD: MutableLiveData<Array<Location>> = MutableLiveData()

        makeRequest("/location", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val locationsRespJSON = JSONObject(resp)
                        val locationsJSON = locationsRespJSON.getJSONArray("results")
                        //val apiINFO = APICallInfo(charactersJSON.getJSONObject("info"))
                        val locations: Array<Location> = Array(locationsJSON.length()) {
                            Location(locationsJSON.getJSONObject(it))
                        }
                        locationsLD.postValue(locations)
                    }
                }
            }

        })
        return locationsLD
    }

    fun getAllEpisodes(): LiveData<Array<Episode>> {
        val episodesLD: MutableLiveData<Array<Episode>> = MutableLiveData()

        makeRequest("/episode", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val episodesRespJSON = JSONObject(resp)
                        val episodesJSON = episodesRespJSON.getJSONArray("results")
                        //val apiINFO = APICallInfo(charactersJSON.getJSONObject("info"))
                        val episodes: Array<Episode> = Array(episodesJSON.length()) {
                            Episode(episodesJSON.getJSONObject(it))
                        }
                        episodesLD.postValue(episodes)
                    }
                }
            }

        })

        return episodesLD
    }

    private fun makeRequest(endpoint: String, callback: Callback) {
        val request = Request.Builder().url("${ServiceINFO.BASE_API}${endpoint}").build()
        apiCLI.newCall(request).enqueue(callback)
    }
}