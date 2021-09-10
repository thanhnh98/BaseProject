package com.thanh_nguyen.baseproject.screens.cgv_clone

import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseCollectionActivityMVVM
import com.thanh_nguyen.baseproject.databinding.ActivityLandingCgvBinding
import com.thanh_nguyen.baseproject.screens.cgv_clone.item.MovieItemRecycleView
import com.thanh_nguyen.baseproject.screens.cgv_clone.item.banner.BannerRecyclerViewItem
import kodeinViewModel
import kotlin.math.abs

class CgvLandingActivity: BaseCollectionActivityMVVM<ActivityLandingCgvBinding, CgvLandingViewModel>() {
    override fun inflateLayout(): Int = R.layout.activity_landing_cgv
    override val viewModel: CgvLandingViewModel by kodeinViewModel()
    override fun initClusters() {
        addCluster(BannerRecyclerViewItem::class.java)
        addCluster(MovieItemRecycleView::class.java)
    }

    override fun onCreateX(savedInstanceState: Bundle?) {
        super.onCreateX(savedInstanceState)
        setupData()
        var percentCollapsed = 0f
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener {
                _, verticalOffset ->
            percentCollapsed = abs(verticalOffset.toFloat()) / binding.appBar.totalScrollRange
            binding.bgTabBar.alpha = percentCollapsed
        })
    }

    private fun setupData() {
        recyclerManager.replace(BannerRecyclerViewItem::class.java, BannerRecyclerViewItem())
        recyclerManager.replace(MovieItemRecycleView::class.java, listOf(
            MovieItemRecycleView(),
            MovieItemRecycleView(),
            MovieItemRecycleView(),
            MovieItemRecycleView(),
            MovieItemRecycleView(),
        ))
    }

}