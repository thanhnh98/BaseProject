package com.thanh_nguyen.baseproject.app.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.billingclient.api.*
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.app.model.BannerModel
import com.thanh_nguyen.baseproject.app.presentation.ui.main.home.item.BannerRecycleViewItem
import com.thanh_nguyen.baseproject.common.base.mvvm.fragment.BaseCollectionFragmentMVVM
import com.thanh_nguyen.baseproject.common.base.mvvm.fragment.BaseFragmentMVVM
import com.thanh_nguyen.baseproject.common.view.banner.BannerViewAdapterModel
import com.thanh_nguyen.baseproject.databinding.FragmentHomeBinding
import kodeinViewModel
import kotlinx.coroutines.*

class HomeFragment: BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override val viewModel: HomeViewModel by kodeinViewModel()

    override fun inflateLayout(): Int = R.layout.fragment_home
}