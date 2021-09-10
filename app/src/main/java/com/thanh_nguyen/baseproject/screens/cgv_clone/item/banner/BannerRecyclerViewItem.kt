package com.thanh_nguyen.baseproject.screens.cgv_clone.item.banner

import android.util.Log
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.app.AppApplication
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemBannerBinding
import com.thanh_nguyen.baseproject.screens.cgv_clone.item.banner.banner_single_item.BannerAdapter
import com.thanh_nguyen.baseproject.utils.createViewHolder
import kotlin.math.abs
import kotlin.math.max


class BannerRecyclerViewItem(): BindingRecycleViewItem<ItemBannerBinding, BannerRecyclerVH>() {
    override fun inflateViewHolder(parent: ViewGroup): BannerRecyclerVH {
        return parent.createViewHolder(R.layout.item_banner){
            BannerRecyclerVH(it)
        }
    }

    override fun bindModel(binding: ItemBannerBinding?, viewHolder: BannerRecyclerVH) {
        binding?.itemViewPager?.also {
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.offscreenPageLimit = 3
            it.adapter = BannerAdapter()
            transformation(it)
        }
    }

    private fun transformation(viewpager: ViewPager2){
        val pageMargin = AppApplication.getContext().resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pageOffset = AppApplication.getContext().resources.getDimensionPixelOffset(R.dimen.offset)

        viewpager.setPageTransformer { page, position ->
            val myOffset: Float = position * -(2 * pageOffset + pageMargin)
            Log.e("pos", "$position")
            when {
                position < -1 -> {
                    page.translationX = -myOffset
                }
                position <= 1 -> {
                    val scaleFactor = max(0.7f, 1 - abs(position - 0.14285715f))
                    page.translationX = myOffset
                    page.scaleY = scaleFactor
                    page.alpha = 1f
                }
                else -> {
                    page.alpha = 0f
                    page.translationX = myOffset
                }
            }
        }
    }
}