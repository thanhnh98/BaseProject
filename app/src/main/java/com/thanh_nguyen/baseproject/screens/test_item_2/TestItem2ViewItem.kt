package com.thanh_nguyen.baseproject.screens.test_item_2

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemTest2Binding

class TestItem2ViewItem: BindingRecycleViewItem<ItemTest2Binding, TestItem2Viewholder>() {
    override fun inflateViewHolder(parent: ViewGroup): TestItem2Viewholder {
        return TestItem2Viewholder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_test_2, null
                )
        )
    }

    override fun bindModel(binding: ItemTest2Binding?, viewHolder: TestItem2Viewholder) {
       binding?.tvTest2?.text = "TEST 2"
    }

}