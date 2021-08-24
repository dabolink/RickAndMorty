package com.dabolink.rickandmorty

import com.dabolink.rickandmorty.models.Character
import com.dabolink.rickandmorty.models.Episode
import com.dabolink.rickandmorty.models.Location
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun characterFromJSON() {
        val charJSON = JSONObject("{\"id\":361,\"name\":\"Toxic Rick\",\"status\":\"Dead\",\"species\":\"Humanoid\",\"type\":\"Rick's toxic side\",\"gender\":\"Male\",\"origin\":{\"name\":\"Detoxifier\",\"url\":\"https://rickandmortyapi.com/api/location/64\"},\"location\":{\"name\":\"Earth (Replacement Dimension)\",\"url\":\"https://rickandmortyapi.com/api/location/20\"},\"image\":\"https://rickandmortyapi.com/api/character/avatar/361.jpeg\",\"episode\":[\"https://rickandmortyapi.com/api/episode/27\"],\"url\":\"https://rickandmortyapi.com/api/character/361\",\"created\":\"2018-01-10T18:20:41.703Z\"}")
        val character = Character(charJSON)

        assertEquals(character.name, "Toxic Rick")
        assertEquals(character.id, 361)
        assertEquals(character.status, "Dead")
        assertEquals(character.species, "Humanoid")
        assertEquals(character.type, "Rick's toxic side")
        assertEquals(character.gender, "Male")
        assertEquals(character.url, "https://rickandmortyapi.com/api/character/361")
        assertEquals(character.image, "https://rickandmortyapi.com/api/character/avatar/361.jpeg")
        assertEquals(character.created_at, "2018-01-10T18:20:41.703Z")
        assertNotNull(character.origin)
        assertNotNull(character.location)
    }

    @Test
    fun locationFromJSON() {
        val locationJSON = JSONObject("{\"id\":1,\"name\":\"Earth (C-137)\",\"type\":\"Planet\",\"dimension\":\"Dimension C-137\",\"residents\":[\"https://rickandmortyapi.com/api/character/38\",\"https://rickandmortyapi.com/api/character/45\",\"https://rickandmortyapi.com/api/character/71\",\"https://rickandmortyapi.com/api/character/82\",\"https://rickandmortyapi.com/api/character/83\",\"https://rickandmortyapi.com/api/character/92\",\"https://rickandmortyapi.com/api/character/112\",\"https://rickandmortyapi.com/api/character/114\",\"https://rickandmortyapi.com/api/character/116\",\"https://rickandmortyapi.com/api/character/117\",\"https://rickandmortyapi.com/api/character/120\",\"https://rickandmortyapi.com/api/character/127\",\"https://rickandmortyapi.com/api/character/155\",\"https://rickandmortyapi.com/api/character/169\",\"https://rickandmortyapi.com/api/character/175\",\"https://rickandmortyapi.com/api/character/179\",\"https://rickandmortyapi.com/api/character/186\",\"https://rickandmortyapi.com/api/character/201\",\"https://rickandmortyapi.com/api/character/216\",\"https://rickandmortyapi.com/api/character/239\",\"https://rickandmortyapi.com/api/character/271\",\"https://rickandmortyapi.com/api/character/302\",\"https://rickandmortyapi.com/api/character/303\",\"https://rickandmortyapi.com/api/character/338\",\"https://rickandmortyapi.com/api/character/343\",\"https://rickandmortyapi.com/api/character/356\",\"https://rickandmortyapi.com/api/character/394\"],\"url\":\"https://rickandmortyapi.com/api/location/1\",\"created\":\"2017-11-10T12:42:04.162Z\"}")
        val location = Location(locationJSON)

        assertEquals(location.name, "Earth (C-137)")
        assertEquals(location.id, 1)
        assertEquals(location.type, "Planet")
        assertEquals(location.dimension, "Dimension C-137")
        assertEquals(location.url, "https://rickandmortyapi.com/api/location/1")
        assertEquals(location.created, "2017-11-10T12:42:04.162Z")
        assertNotNull(location.residents)
        assertEquals(location.residents.size, 27)

    }

    @Test
    fun episodeFromJSON() {
        val episodeJSON = JSONObject("{\"id\":1,\"name\":\"Pilot\",\"air_date\":\"December 2, 2013\",\"episode\":\"S01E01\",\"characters\":[\"https://rickandmortyapi.com/api/character/1\",\"https://rickandmortyapi.com/api/character/2\",\"https://rickandmortyapi.com/api/character/35\",\"https://rickandmortyapi.com/api/character/38\",\"https://rickandmortyapi.com/api/character/62\",\"https://rickandmortyapi.com/api/character/92\",\"https://rickandmortyapi.com/api/character/127\",\"https://rickandmortyapi.com/api/character/144\",\"https://rickandmortyapi.com/api/character/158\",\"https://rickandmortyapi.com/api/character/175\",\"https://rickandmortyapi.com/api/character/179\",\"https://rickandmortyapi.com/api/character/181\",\"https://rickandmortyapi.com/api/character/239\",\"https://rickandmortyapi.com/api/character/249\",\"https://rickandmortyapi.com/api/character/271\",\"https://rickandmortyapi.com/api/character/338\",\"https://rickandmortyapi.com/api/character/394\",\"https://rickandmortyapi.com/api/character/395\",\"https://rickandmortyapi.com/api/character/435\"],\"url\":\"https://rickandmortyapi.com/api/episode/1\",\"created\":\"2017-11-10T12:56:33.798Z\"}")
        val episode = Episode(episodeJSON)

        assertEquals(episode.id, 1)
        assertEquals(episode.name, "Pilot")
        assertEquals(episode.airDate, "December 2, 2013")
        assertEquals(episode.episode, "S01E01")
        assertEquals(episode.url, "https://rickandmortyapi.com/api/episode/1")
        assertEquals(episode.created, "2017-11-10T12:56:33.798Z")
        assertNotNull(episode.characters)
        assertEquals(episode.characters.size, 19)


    }
}