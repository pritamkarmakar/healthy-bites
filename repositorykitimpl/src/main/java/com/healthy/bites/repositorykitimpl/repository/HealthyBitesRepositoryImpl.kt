package com.healthy.bites.repositorykitimpl.repository

import com.healthy.bites.models.ArticleAPIResponse
import com.healthy.bites.remote.RemoteDataSource
import com.healthy.bites.repositorykit.HealthyBitesRepository
import io.reactivex.Observable

/**
 * repository for the app, instance will be available through AppComponent
 */
class HealthyBitesRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    HealthyBitesRepository {
    override fun getArticles(
        query: String,
        page: Int,
        apiKey: String,
        sort: String,
        beginDate: String,
        endDate: String
    ): Observable<ArticleAPIResponse> {
        return remoteDataSource.fetchArticles(query, page, apiKey, sort, beginDate, endDate)
    }
}