package com.dabolink.rickandmorty.services

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dabolink.rickandmorty.models.APICallInfo
import com.dabolink.rickandmorty.models.Character
import com.dabolink.rickandmorty.models.Episode
import com.dabolink.rickandmorty.models.Location
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class RAMService {
    private val apiCLI = OkHttpClient()
    var loadingCharacterData: Boolean = false
    var loadingEpisodeData: Boolean = false
    var loadingLocationData: Boolean = false
    var currentCharacterInfo: APICallInfo? = null
    var currentEpisodeInfo: APICallInfo? = null
    var currentLocationInfo: APICallInfo? = null
    val charactersLD = MutableLiveData<ArrayList<Character>>(ArrayList())
    val episodesLD = MutableLiveData<ArrayList<Episode>>(ArrayList())
    val locationsLD = MutableLiveData<ArrayList<Location>>(ArrayList())

    fun loadCharacters() {
        if(loadingCharacterData) {
            return
        }
        loadingCharacterData = true
        val nextURI = getNextUri(currentCharacterInfo, "/character")
        if(nextURI == null) {
            loadingCharacterData = false
            return
        }

        Log.d("RAMService", nextURI)


        makeRequest(nextURI, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
                loadingCharacterData = false
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val charactersRespJSON = JSONObject(resp)
                        val charactersJSON = charactersRespJSON.getJSONArray("results")
                        currentCharacterInfo = APICallInfo(charactersRespJSON.getJSONObject("info"))

                        val allCharacters = charactersLD.value ?: ArrayList()

                        val characters: ArrayList<Character> = ArrayList(charactersJSON.length())
                        for (i in 0 until charactersJSON.length()) {
                            characters.add(Character(charactersJSON.getJSONObject(i)))
                        }

                        allCharacters.addAll(characters)

                        charactersLD.postValue(allCharacters)

                    }
                }
                loadingCharacterData = false
            }
        })
    }

    fun loadLocations() {
        if(loadingLocationData) {
            return
        }
        loadingLocationData = true
        val nextURI = getNextUri(currentLocationInfo, "/location")
        if(nextURI == null) {
            loadingLocationData = false
            return
        }

        Log.d("RAMService", nextURI)

        makeRequest(nextURI, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
                loadingLocationData = false
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val locationsRespJSON = JSONObject(resp)
                        val locationsJSON = locationsRespJSON.getJSONArray("results")
                        currentLocationInfo = APICallInfo(locationsRespJSON.getJSONObject("info"))

                        val allLocations = locationsLD.value ?: ArrayList()

                        val locations: ArrayList<Location> = ArrayList(locationsJSON.length())
                        for (i in 0 until locationsJSON.length()) {
                            locations.add(Location(locationsJSON.getJSONObject(i)))
                        }

                        allLocations.addAll(locations)

                        locationsLD.postValue(allLocations)
                    }
                }
                loadingLocationData = false
            }

        })
    }

    fun loadEpisodes() {
        if(loadingEpisodeData) {
            Log.d("RAMService", "already loading data... please hold")
            return
        }
        loadingEpisodeData = true

        val nextURI = getNextUri(currentEpisodeInfo, "/episode")
        if(nextURI == null) {
            loadingEpisodeData = false
            return
        }

        Log.d("RAMService", nextURI)

        makeRequest(nextURI, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
                loadingEpisodeData = false
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val episodesRespJSON = JSONObject(resp)
                        val episodesJSON = episodesRespJSON.getJSONArray("results")
                        currentEpisodeInfo = APICallInfo(episodesRespJSON.getJSONObject("info"))

                        val allEpisodes = episodesLD.value ?: ArrayList()

                        val episodes: ArrayList<Episode> = ArrayList(episodesJSON.length())
                        for (i in 0 until episodesJSON.length()) {
                            episodes.add(Episode(episodesJSON.getJSONObject(i)))
                        }

                        allEpisodes.addAll(episodes)

                        episodesLD.postValue(allEpisodes)
                    }
                } else {
                    println(response)
                }
                loadingEpisodeData = false
            }

        })
    }

    fun loadCharacter(id: Int): LiveData<Character> {
        val characterLD: MutableLiveData<Character> = MutableLiveData()
        val uri = createURI("/character/$id")
        makeRequest(uri.toString(), object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body()?.string()?.let { resp ->
                        val characterJSON = JSONObject(resp)
                        println(characterJSON)
                        characterLD.postValue(Character(characterJSON))
                    }
                }
            }

        })
        return characterLD
    }

    private fun makeRequest(url: String, callback: Callback) {
        val request = Request.Builder()
            .method("GET", null)
            .url(url).build()
        apiCLI.newCall(request).enqueue(callback)
    }

    fun reloadCharacters() {
        currentCharacterInfo = null
        charactersLD.postValue(ArrayList())
        loadCharacters()
    }

    companion object {
        private const val BASE_API: String = "https://rickandmortyapi.com/api"

        private fun createURI(endpoint: String): Uri {
            return Uri.parse("$BASE_API$endpoint")
        }


        private fun getNextUri(apiCall: APICallInfo?, endpoint: String): String? {
            if(apiCall == null) {
                return createURI(endpoint).toString()
            }
            return apiCall.next
        }
    }
}