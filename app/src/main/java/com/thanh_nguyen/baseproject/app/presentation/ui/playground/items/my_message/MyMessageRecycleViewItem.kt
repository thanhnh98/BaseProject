package com.thanh_nguyen.baseproject.app.presentation.ui.playground.items.my_message

import android.view.ViewGroup
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemMyMessageBinding
import com.thanh_nguyen.baseproject.utils.inflateView

class MyMessageRecycleViewItem(val msg: String): BindingRecycleViewItem<ItemMyMessageBinding, MyMessageRecyclerVH>() {
    override fun inflateViewHolder(parent: ViewGroup): MyMessageRecyclerVH {
        return MyMessageRecyclerVH(
            inflateView(
                parent,
                R.layout.item_my_message
            )
        )
    }

    override fun bindModel(binding: ItemMyMessageBinding?, viewHolder: MyMessageRecyclerVH) {
        binding?.tvMessage?.text = msg
    }
}