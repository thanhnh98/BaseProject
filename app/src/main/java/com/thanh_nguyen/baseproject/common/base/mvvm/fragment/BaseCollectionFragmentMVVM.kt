package com.thanh_nguyen.baseproject.common.base.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.EndlessRecyclerViewScrollListener
import com.thanh_nguyen.baseproject.common.base.adapter.RecyclerManager
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseCollectionViewModel

abstract class BaseCollectionFragmentMVVM<DB: ViewDataBinding, VM: BaseCollectionViewModel> : BaseFragmentMVVM<DB, VM>() {
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val SPAN_COUNT = 2
    private var isLoadingMore = false
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    private var recyclerManager = RecyclerManager<Any>()
    var gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateViewX(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        recyclerView = binding.root.findViewById(R.id.recycler_view)
        swipeRefresh = binding.root.findViewById(R.id.swipe_refresh)
    }

    private fun initRecyclerView() {
        scrollListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (shouldLoadMore()) {
                    viewModel.invokeLoadMore()
                }
            }
        }
    }

    open fun resetScrollingState(){
        scrollListener.resetState()
    }

    open fun onScrollingUp(){

    }

    open fun onScrollingDown(){

    }

    open fun onBackingToTop(shouldShow: Boolean){

    }

    open fun onRefresh(){

    }

    override fun hideLoading() {
        super.hideLoading()
        swipeRefresh.isRefreshing = false
    }

    override fun showLoading() {
        super.showLoading()
        swipeRefresh.isRefreshing = true
    }

    open fun shouldLoadMore(): Boolean = true

    open fun shouldPullToRefresh(): Boolean = true
}