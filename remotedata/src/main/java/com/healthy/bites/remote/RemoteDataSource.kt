package com.healthy.bites.remote

import com.healthy.bites.models.ArticleAPIResponse
import com.healthy.bites.remote.api.HealthyBitesApi
import io.reactivex.Observable

interface RemoteDataSource {
    fun fetchArticles(
        query: String,
        page: Int,
        apiKey: String,
        sort: String,
        beginDate: String,
        endDate: String
    ): Observable<ArticleAPIResponse>
}

class RemoteDataSourceImpl(private val healthyBitesApi: HealthyBitesApi) : RemoteDataSource {
    override fun fetchArticles(
        query: String,
        page: Int,
        apiKey: String,
        sort: String,
        beginDate: String,
        endDate: String
    ): Observable<ArticleAPIResponse> {
        return healthyBitesApi.fetchArticles(query, page, apiKey, sort, beginDate, endDate)
    }
}