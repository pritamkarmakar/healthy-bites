package com.healthy.bites.landingscreen.viewmodel

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.healthy.bites.basekit.SchedulerProvider
import com.healthy.bites.basekit.ToastMaker
import com.healthy.bites.landingscreen.R
import com.healthy.bites.landingscreen.adapter.HealthyBitesAdapter
import com.healthy.bites.landingscreen.navigator.Navigator
import com.healthy.bites.landingscreen.utils.*
import com.healthy.bites.landingscreen.utils.API_KEY
import com.healthy.bites.landingscreen.utils.END_DATE
import com.healthy.bites.landingscreen.utils.SEARCH_QUERY
import com.healthy.bites.landingscreen.utils.SORT_ORDER
import com.healthy.bites.models.Article
import com.healthy.bites.models.ArticleAPIResponse
import com.healthy.bites.repositorykit.HealthyBitesRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val healthyBitesRepository: HealthyBitesRepository,
    private val schedulerProvider: SchedulerProvider,
    private val toastMaker: ToastMaker
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    // this will be binded with Recycler view
    val apiResult: ObservableField<List<Article>> = ObservableField()

    // this list to keep total articles received from the API, we will use this to bind to apiResult observable
    // in case user makes any configurations changes like screen rotation etc.
    private val articlesReceived: MutableList<Article> = mutableListOf()
    var progressbarVisibility: ObservableInt = ObservableInt(View.INVISIBLE)
    var isScrolling: ObservableBoolean = ObservableBoolean(false)
    var page = 0

    fun loadDataInit() {
        val isDataEmpty = apiResult.get()?.isEmpty() ?: true
        if (isDataEmpty) {
            compositeDisposable.add(
                healthyBitesRepository.getArticles(
                    SEARCH_QUERY,
                    page,
                    API_KEY,
                    SORT_ORDER,
                    BEGIN_DATE,
                    END_DATE
                )
                    .subscribeOn(schedulerProvider.ioSchedulerProvider())
                    .doOnSubscribe { progressbarVisibility.set(View.VISIBLE) }
                    .observeOn(schedulerProvider.uiSchedulerProvider())
                    .doFinally { progressbarVisibility.set(View.INVISIBLE) }
                    .subscribeBy(
                        onNext = {
                            articlesReceived.addAll(it.response.docs)
                            apiResult.set(it.response.docs)
                        },
                        onError = {
                            toastMaker.showToast(R.string.server_error_message)
                        }
                    )
            )
        } else {
            // this will execute when there is a configuration change we're using the same view model
            apiResult.set(articlesReceived)
        }
    }

    /**
     * to load more data once user starts scrolling
     */
    fun loadMoreData() {
        page += 1
        compositeDisposable.add(healthyBitesRepository.getArticles(
            SEARCH_QUERY,
            page,
            API_KEY,
            SORT_ORDER,
            BEGIN_DATE,
            END_DATE
        )
            .subscribeOn(schedulerProvider.ioSchedulerProvider())
            .doOnSubscribe { progressbarVisibility.set(View.VISIBLE) }
            .observeOn(schedulerProvider.uiSchedulerProvider())
            .doFinally { progressbarVisibility.set(View.INVISIBLE) }
            .subscribeBy(
                onNext = {
                    articlesReceived.addAll(it.response.docs)
                    apiResult.set(it.response.docs)
                },
                onError = {
                    toastMaker.showToast(R.string.server_error_message)
                }
            )
        )
    }

    override fun onCleared() {
        // dispose all disposable
        super.onCleared()
        compositeDisposable.clear()
    }
}