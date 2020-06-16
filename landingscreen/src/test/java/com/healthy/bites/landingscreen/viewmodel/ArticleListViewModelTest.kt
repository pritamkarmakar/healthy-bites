package com.healthy.bites.landingscreen.viewmodel

import com.healthy.bites.basekit.SchedulerProvider
import com.healthy.bites.basekit.ToastMaker
import com.healthy.bites.landingscreen.utils.*
import com.healthy.bites.models.Article
import com.healthy.bites.models.ArticleAPIResponse
import com.healthy.bites.models.Meta
import com.healthy.bites.models.Response
import com.healthy.bites.repositorykit.HealthyBitesRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArticleListViewModelTest {
    private lateinit var viewModel: ArticleListViewModel
    private val healthyBitesRepository: HealthyBitesRepository = mock()
    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()
    private val toastMaker: ToastMaker = mock()
    private val page = 0

    @Before
    fun setUp() {
        viewModel = ArticleListViewModel(healthyBitesRepository, schedulerProvider, toastMaker)
    }

    @Test
    fun `verify loadDataInit when apiResult is empty`() {
        val apiResponse = createArticleAPIResponse()
        val apiResult = Observable.just(apiResponse)
        whenever(
            healthyBitesRepository.getArticles(
                SEARCH_QUERY,
                page,
                API_KEY,
                SORT_ORDER,
                BEGIN_DATE,
                END_DATE
            )
        ).thenReturn(apiResult)
        viewModel.loadDataInit()
        Assert.assertEquals(apiResponse.response.docs, viewModel.apiResult.get())
    }

    @Test
    fun `verify loadDataInit when apiResult not empty`() {
        val apiResponse = createArticleAPIResponse()
        val apiResult = Observable.just(apiResponse)
        whenever(
            healthyBitesRepository.getArticles(
                SEARCH_QUERY,
                page,
                API_KEY,
                SORT_ORDER,
                BEGIN_DATE,
                END_DATE
            )
        ).thenReturn(apiResult)
        viewModel.loadDataInit()
        viewModel.loadDataInit()
        Assert.assertEquals(apiResponse.response.docs, viewModel.apiResult.get())
    }

    @Test
    fun loadMoreData() {
        val apiResponse = createArticleAPIResponse()
        val apiResult = Observable.just(apiResponse)
        whenever(
            healthyBitesRepository.getArticles(
                SEARCH_QUERY,
                1,
                API_KEY,
                SORT_ORDER,
                BEGIN_DATE,
                END_DATE
            )
        ).thenReturn(apiResult)
        viewModel.loadMoreData()
        Assert.assertEquals(apiResponse.response.docs, viewModel.apiResult.get())
    }

    private fun createArticleAPIResponse(): ArticleAPIResponse {
        val articles: MutableList<Article> = mutableListOf()
        val article1: Article = mock()
        val article2: Article = mock()
        articles.add(article1)
        articles.add(article2)
        val meta: Meta = mock()
        return ArticleAPIResponse(
            copyright = "copyright",
            status = "status",
            response = Response(docs = articles, meta = meta)
        )
    }
}