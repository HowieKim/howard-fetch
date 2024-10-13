package com.howie.fetch.data.remote.rest

import com.howie.fetch.data.remote.models.FetchJson
import retrofit2.http.GET

interface FetchService {

    @GET("hiring.json")
    suspend fun getFetchData(): List<FetchJson>
}