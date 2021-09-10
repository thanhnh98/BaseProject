package com.thanh_nguyen.baseproject.common.base.mvvm.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.EndlessRecyclerViewScrollListener
import com.thanh_nguyen.baseproject.common.base.adapter.RecyclerManager
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseCollectionViewModel

abstract class BaseCollectionActivityMVVM<DB: ViewDataBinding, VM: BaseCollectionViewModel>: BaseActivityMVVM<DB, VM>() {
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    var recyclerManager = RecyclerManager<Any>()
    var gridLayoutManager = GridLayoutManager(this, spanCount())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateX(savedInstanceState: Bundle?) {
        recyclerView = binding.root.findViewById(R.id.recycler_view)
        swipeRefresh = binding.root.findViewById(R.id.swipe_refresh)
        addBottomLoadingItem()
        initClusters()
        initRecyclerView()
    }

    private fun addBottomLoadingItem() {
    }

    private fun initRecyclerView() {
        scrollListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (shouldLoadMore()) {
                    viewModel.invokeLoadMore()
                }
            }
        }

        with(recyclerView){
            layoutManager = gridLayoutManager
            adapter = recyclerManager.adapter
            addOnScrollListener(scrollListener)
        }


        swipeRefresh.also {
            it.setOnRefreshListener {
                if (shouldPullToRefresh())
                    onRefresh()
                else
                    hideLoading()
            }
            it.setColorSchemeColors(
                ContextCompat.getColor(
                    this,
                    R.color.purple_200
                )
            )
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

    abstract fun initClusters()

    fun addCluster(cluster: Any){
        recyclerManager.addCluster(cluster)
    }

    open fun shouldPullToRefresh(): Boolean = true

    open fun isReverseLayout() = false

    fun spanCount() = 1
}