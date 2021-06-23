package com.thanh_nguyen.baseproject.screens

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.BaseRecycleViewItem

class TestViewItem: BaseRecycleViewItem<TestViewHolder>() {
    override fun bind(viewHolder: TestViewHolder?) {

    }

    override fun createViewHolder(context: Context?): RecyclerView.ViewHolder {
        return TestViewHolder(LayoutInflater.from(context).inflate(R.layout.item_test, null))
    }
}