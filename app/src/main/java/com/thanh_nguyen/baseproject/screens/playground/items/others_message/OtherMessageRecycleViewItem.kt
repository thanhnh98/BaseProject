package com.thanh_nguyen.baseproject.screens.playground.items.others_message

import android.view.ViewGroup
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemOtherMessageBinding
import com.thanh_nguyen.baseproject.utils.createViewHolder

class OtherMessageRecycleViewItem(val msg: String): BindingRecycleViewItem<ItemOtherMessageBinding, OtherMessageRecycleVH>() {
    override fun inflateViewHolder(parent: ViewGroup): OtherMessageRecycleVH {
        return parent.createViewHolder(
            R.layout.item_other_message
        ){
            OtherMessageRecycleVH(it)
        }
    }

    override fun bindModel(binding: ItemOtherMessageBinding?, viewHolder: OtherMessageRecycleVH) {
        binding?.tvMessage?.text = msg
    }
}