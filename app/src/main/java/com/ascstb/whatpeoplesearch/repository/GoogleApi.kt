package com.ascstb.whatpeoplesearch.repository

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {
    @GET("complete/search")
    fun getAnswersAsync(
        @Query("client") client: String,
        @Query("hl") language: String,
        @Query("q") search: String
    ) : Deferred<ResponseBody>
}