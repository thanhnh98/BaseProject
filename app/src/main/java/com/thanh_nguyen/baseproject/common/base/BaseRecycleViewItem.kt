package com.thanh_nguyen.baseproject.common.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewItem<VH: RecyclerView.ViewHolder>  {
    fun getType(): Int {
        return this.javaClass.hashCode()
    }

    abstract fun bind(viewHolder: VH?)

    abstract fun createViewHolder(context: Context?): RecyclerView.ViewHolder?
}