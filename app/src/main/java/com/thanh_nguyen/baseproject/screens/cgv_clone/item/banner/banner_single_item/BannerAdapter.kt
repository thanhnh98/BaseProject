package com.thanh_nguyen.baseproject.screens.cgv_clone.item.banner.banner_single_item

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.utils.createViewHolder

class BannerAdapter: RecyclerView.Adapter<BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return parent.createViewHolder(R.layout.item_single_banner){
            BannerViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.bg.setBackgroundColor(Color.RED)
        } else {
            holder.bg.setBackgroundColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int {
        return 10
    }


}

class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val bg: View = itemView.findViewById(R.id.v_background)
}

