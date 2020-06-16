package com.healthy.bites.repositorykitimpl.repository

import com.healthy.bites.models.ArticleAPIResponse
import com.healthy.bites.remote.RemoteDataSource
import com.healthy.bites.repositorykit.HealthyBitesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HealthyBitesRepositoryImplTest {
    private lateinit var repository: HealthyBitesRepository
    private val remoteDataSource: RemoteDataSource = mock()
    private val articleAPIResponse: ArticleAPIResponse = mock()
    private val query = "query"
    private val page = 0;
    private val apiKey = "api_key"
    private val sort = "sort"
    private val beginDate = "20200101"
    private val endDate = "20201201"

    @Before
    fun setup() {
        repository = HealthyBitesRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `verify getArticles`() {
        val apiResult = Observable.just(articleAPIResponse)
        whenever(
            remoteDataSource.fetchArticles(
                query,
                page,
                apiKey,
                sort,
                beginDate,
                endDate
            )
        ).thenReturn(apiResult)
        val result = repository.getArticles(query, page, apiKey, sort, beginDate, endDate)
        verify(remoteDataSource).fetchArticles(query, page, apiKey, sort, beginDate, endDate)
        Assert.assertEquals(apiResult, result)
    }
}