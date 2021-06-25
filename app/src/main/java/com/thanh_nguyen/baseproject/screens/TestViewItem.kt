package com.thanh_nguyen.baseproject.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemTestBinding

class TestViewItem: BindingRecycleViewItem<ItemTestBinding, TestViewHolder>() {

    override fun inflateViewHolder(parent: ViewGroup): TestViewHolder {
        return TestViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_test, null)
        )
    }

    override fun bindModel(binding: ItemTestBinding?, viewHolder: TestViewHolder) {
        binding?.tvTest?.text = "HAHAHAHA BINDING"
    }

}