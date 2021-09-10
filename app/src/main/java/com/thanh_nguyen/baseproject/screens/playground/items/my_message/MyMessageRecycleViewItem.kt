package com.thanh_nguyen.baseproject.screens.playground.items.my_message

import android.view.ViewGroup
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemMyMessageBinding
import com.thanh_nguyen.baseproject.utils.createViewHolder

class MyMessageRecycleViewItem(private val msg: String): BindingRecycleViewItem<ItemMyMessageBinding, MyMessageRecyclerVH>() {
    override fun inflateViewHolder(parent: ViewGroup): MyMessageRecyclerVH {
        return parent.createViewHolder(R.layout.item_my_message){
            MyMessageRecyclerVH(it)
        }
    }

    override fun bindModel(binding: ItemMyMessageBinding?, viewHolder: MyMessageRecyclerVH) {
        binding?.tvMessage?.text = msg
    }
}