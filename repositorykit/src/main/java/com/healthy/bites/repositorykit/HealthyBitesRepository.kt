package com.healthy.bites.repositorykit

import com.healthy.bites.models.ArticleAPIResponse
import io.reactivex.Observable

/**
 * repository to be used by the entire app, implementation in repositorykitimpl module
 */
interface HealthyBitesRepository {
    fun getArticles(
        query: String,
        page: Int,
        apiKey: String,
        sort: String,
        beginDate: String,
        endDate: String
    ): Observable<ArticleAPIResponse>
}