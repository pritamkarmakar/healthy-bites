package com.healthy.bites.landingscreen.bindingadapter

import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthy.bites.landingscreen.adapter.HealthyBitesAdapter
import com.healthy.bites.landingscreen.viewmodel.ArticleListViewModel
import com.healthy.bites.models.Article

@BindingAdapter("app:updateRecyclerView")
fun updateRecyclerView(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as? HealthyBitesAdapter
    data?.let { adapter?.setData(it) }
}

@BindingAdapter("app:addOnScrollListener")
fun addOnScrollListener(
    recyclerView: RecyclerView,
    viewModel: ArticleListViewModel
) {
    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisible = layoutManager.findFirstVisibleItemPosition()
            viewModel.run {
                if (isScrolling.get() && (visibleItemCount + firstVisible) >= totalItemCount) {
                    isScrolling.set(false)
                    loadMoreData()
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                viewModel.isScrolling.set(true)
            }
        }
    })
}

@BindingAdapter("android:layout_marginLeft")
fun bindInputLeftMargin(view: View, leftMargin: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.setMargins(
        leftMargin.toInt(),
        layoutParams.topMargin,
        layoutParams.rightMargin,
        layoutParams.bottomMargin
    )
    view.layoutParams = layoutParams
}