package com.healthy.bites.remote.api

import com.healthy.bites.models.ArticleAPIResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HealthyBitesApi {
    @GET("/svc/search/v2/articlesearch.json")
    fun fetchArticles(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("api-key") apiKey: String,
        @Query("sort") sort: String,
        @Query("begin_date") begin_date: String,
        @Query("end_date") end_date: String
    ): Observable<ArticleAPIResponse>
}