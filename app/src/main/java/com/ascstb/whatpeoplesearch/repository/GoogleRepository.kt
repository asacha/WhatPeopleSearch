package com.ascstb.whatpeoplesearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ascstb.whatpeoplesearch.model.GoogleAnswer
import kotlinx.coroutines.runBlocking
import org.json.JSONArray

interface GoogleRepository {
    fun getAnswersAsync(language: String, query: String): LiveData<List<GoogleAnswer>>
}

class GoogleRepositoryImpl(private val googleApi: GoogleApi) : GoogleRepository {
    private var answersData = MutableLiveData<List<GoogleAnswer>>()

    override fun getAnswersAsync(language: String, query: String): LiveData<List<GoogleAnswer>> {
        runBlocking {
            googleApi.getAnswersAsync("firefox", language, "${query.trim()} ").await().string().let { response ->
                if (response.isEmpty()) return@runBlocking
                answersData.value = convertToAnswersList(response)
            }
        }

        return answersData
    }

    private fun convertToAnswersList(response: String): List<GoogleAnswer> {
        val result = mutableListOf<GoogleAnswer>()

        response.apply {
            //region Get Key
            val startIndexOfKey = indexOfFirst { it == '"' } + 1
            val endIndexOfKey = indexOf('"', startIndexOfKey + 1)
            val key = response.substring(startIndexOfKey, endIndexOfKey)
            //endregion

            //region Get Response List
            val stringContent = response.substring(endIndexOfKey + 2, response.length - 1)
            val answersJson = JSONArray(stringContent)
            //endregion

            //region Format result
            for (i in 0 until answersJson.length()) {
                val answer = answersJson.get(i).toString().toLowerCase().replace(key.toLowerCase().trim(), "").trim()
                if (answer.isNotEmpty()) result.add(GoogleAnswer(
                    position = i + 1,
                    answer = answer,
                    points = 10000 - (1000 * i)
                ))
            }
            //endregion
        }

        return result
    }
}