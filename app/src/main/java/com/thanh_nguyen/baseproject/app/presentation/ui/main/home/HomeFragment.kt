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
import com.thanh_nguyen.baseproject.common.view.banner.BannerViewAdapterModel
import com.thanh_nguyen.baseproject.databinding.FragmentHomeBinding
import kodeinViewModel
import kotlinx.coroutines.*

class HomeFragment: BaseCollectionFragmentMVVM<FragmentHomeBinding, HomeViewModel>() {
    private val autoScrollingJob = Job()

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            // To be implemented in a later section.
        }

    private val billingClient by lazy {
        BillingClient.newBuilder(requireContext())
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
    }


    override fun onCreateViewX(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.onCreateViewX(inflater, container, savedInstanceState)
        recyclerManager.replace(BannerRecycleViewItem::class, BannerRecycleViewItem(
            listOf(
                BannerViewAdapterModel.HomeHorizontalBannerModel(
                    BannerModel(
                        "https://static.vecteezy.com/system/resources/previews/000/677/302/non_2x/abstract-technology-banner-background.jpg"
                    )
                ),
                BannerViewAdapterModel.HomeHorizontalBannerModel(
                    BannerModel(
                        "https://image.freepik.com/free-vector/abstract-dotted-banner-background_1035-18160.jpg"
                    )
                ),
                BannerViewAdapterModel.HomeHorizontalBannerModel(
                    BannerModel(
                        "https://png.pngtree.com/thumb_back/fh260/back_pic/02/50/63/71577e1cf59d802.jpg"
                    )
                ),
            ),
        ) {
            GlobalScope.launch {
                querySkuDetails()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    suspend fun querySkuDetails() {
        val skuList = ArrayList<String>()
        skuList.add("premium_upgrade")
        skuList.add("gas")
        val params = SkuDetailsParams.newBuilder()
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)

        // leverage querySkuDetails Kotlin extension function
        val skuDetailsResult = withContext(Dispatchers.IO) {
            billingClient.querySkuDetails(params.build())
        }

        // Process the result.
    }

    override fun initClusters() {
        addCluster(BannerRecycleViewItem::class)
    }

    override val viewModel: HomeViewModel by kodeinViewModel()

    override fun inflateLayout(): Int = R.layout.fragment_home

    override fun onDestroy() {
        super.onDestroy()
        autoScrollingJob.cancel()
    }
}