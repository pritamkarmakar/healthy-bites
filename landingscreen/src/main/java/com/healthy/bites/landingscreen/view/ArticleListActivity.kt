package com.healthy.bites.landingscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthy.bites.landingscreen.R
import com.healthy.bites.landingscreen.adapter.HealthyBitesAdapter
import com.healthy.bites.landingscreen.databinding.ActivityArticleListBinding
import com.healthy.bites.landingscreen.di.component.ArticleListDependencies
import com.healthy.bites.landingscreen.di.component.ArticleListDependenciesProvider
import com.healthy.bites.landingscreen.di.component.DaggerArticleListActivityComponent
import com.healthy.bites.landingscreen.di.module.ArticleListModule
import com.healthy.bites.landingscreen.viewmodel.ArticleListViewModel
import javax.inject.Inject

class ArticleListActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: HealthyBitesAdapter
    private lateinit var databinding: ActivityArticleListBinding
    private lateinit var viewModel: ArticleListViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDI()
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModeFactory).get(ArticleListViewModel::class.java)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_article_list)
        databinding.viewmodel = viewModel
        this.viewModel.loadDataInit()
        // setting up recycler view
        recyclerView = databinding.articleListRecyclerView
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    /**
     * Build Dagger dependency graph
     */
    private fun setupDI() {
        val dependencies: ArticleListDependencies =
            (applicationContext as ArticleListDependenciesProvider).dependencyProvider()

        val component = DaggerArticleListActivityComponent
            .builder()
            .articleListDependencies(dependencies)
            .articleListModule(ArticleListModule(this))
            .build()
        component.inject(this)
    }
}
