package com.thanh_nguyen.baseproject.common.base

import android.os.Parcelable
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import java.lang.String
import java.util.*

/**
 * Created by thongbeo on 9/20/16.
 */
class RecycleViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItems: MutableList<BaseRecycleViewItem<RecyclerView.ViewHolder>>
    private val mPrototypeItem: MutableMap<Int, BaseRecycleViewItem<RecyclerView.ViewHolder>>
    private val mStateSaved: MutableMap<Int, Parcelable?>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mPrototypeItem[viewType]?.createViewHolder(parent.context)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mItems[position].bind(holder)
    }

    val adapter: RecyclerView.Adapter<*>
        get() = this

    override fun getItemViewType(position: Int): Int {
        return mItems[position].getType()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun append(item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        append(mItems.size, item)
    }

    fun append(position: Int, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        mItems.add(position, item)
        addPrototypeItem(item)
        notifyItemInserted(position)
    }

    fun append(items: List<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        append(mItems.size, items)
    }

    fun append(position: Int, items: List<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        mItems.addAll(position, items)
        addPrototypeItem(items)
        notifyItemRangeInserted(position, items.size)
    }

    fun remove(position: Int) {
        validPosition(position)
        mItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(position: Int, length: Int) {
        if (length == 1) {
            remove(position)
        } else {
            val items: MutableList<BaseRecycleViewItem<RecyclerView.ViewHolder>> = ArrayList()
            for (i in 0 until mItems.size) {
                if (i < position || i >= position + length) items.add(mItems[i])
            }
            mItems = items
            notifyItemRangeRemoved(position, length)
        }
    }

    fun replace(position: Int, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        validPosition(position)
        mItems.removeAt(position)
        mItems.add(position, item)
        addPrototypeItem(item)
        notifyItemChanged(position)
    }

    fun refresh(items: MutableList<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        mItems = items
        mPrototypeItem.clear()
        addPrototypeItem(items)
        notifyDataSetChanged()
    }

    val length: Int
        get() = mItems.size

    private fun addPrototypeItem(item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        if (!mPrototypeItem.containsKey(item.hashCode())) {
            mPrototypeItem[item.getType()] = item
        }
    }

    private fun addPrototypeItem(items: List<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        for (item in items) {
            if (!mPrototypeItem.containsKey(item.hashCode())) {
                mPrototypeItem[item.getType()] = item
            }
        }
    }

    fun validPosition(position: Int) {
        check(!(position < 0 || position > mItems.size)) {
            String.format(
                "Recycler-Adapter: Out off range: " +
                        "pos %s length ", position, mItems.size
            )
        }
    }

    init {
        mItems = ArrayList()
        mPrototypeItem = HashMap<Int, BaseRecycleViewItem<RecyclerView.ViewHolder>>()
        mStateSaved = HashMap()
    }
}