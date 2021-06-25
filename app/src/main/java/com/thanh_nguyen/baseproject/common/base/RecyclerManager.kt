package com.thanh_nguyen.baseproject.common.base

import androidx.recyclerview.widget.RecyclerView
import com.thanh_nguyen.baseproject.common.base.BaseRecycleViewItem
import com.thanh_nguyen.baseproject.common.base.RecycleViewAdapter
import java.util.*

class RecyclerManager<T> {
    // Manage group type
    private val mClusters: MutableList<T>

    // Manage map group range
    private val mMapRenderRange: MutableMap<T, RenderRange>
    var adapter: RecycleViewAdapter

    /*
      *  Public method
      * */
    fun recycle() {
        mClusters.clear()
        mMapRenderRange.clear()
    }

    fun addCluster(t: T) {
        mClusters.add(t)
        mMapRenderRange[t] = RenderRange(0, 0)
        calculateMapRenderRange()
    }

    /*
    *  Method notify change recycle
    *  Position: position item in cluster
    * */
    fun append(t: T, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        val renderRange = mMapRenderRange[t]
        append(t, renderRange!!.length, item)
    }

    fun append(t: T, position: Int, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        var position = position
        val renderRange = mMapRenderRange[t]
        validPosition(renderRange, position)

        // find position append
        position += renderRange!!.position

        // update render range
        val length = renderRange.length + 1
        mMapRenderRange[t] = RenderRange(renderRange.position, length)

        // notify
        adapter.append(position, item)
        calculateMapRenderRange()
    }

    fun append(t: T, items: List<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        val renderRange = mMapRenderRange[t]
        append(t, renderRange!!.length, items)
    }

    fun append(t: T, position: Int, items: List<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        var position = position
        val renderRange = mMapRenderRange[t]
        validPosition(renderRange, position)

        // find position append
        position += renderRange!!.position

        // update render range
        val length = renderRange.length + items.size
        mMapRenderRange[t] = RenderRange(renderRange.position, length)

        // notify
        adapter.append(position, items)
        calculateMapRenderRange()
    }

    fun update(t: T, position: Int) {
        var position = position
        val renderRange = mMapRenderRange[t]
        position = position + renderRange!!.position
        adapter.notifyItemChanged(position)
    }

    fun update(t: T, position: Int, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        replace(t, position, item)
    }

    fun remove(t: T, position: Int) {
        var position = position
        if (!mClusters.contains(t) || mMapRenderRange[t]!!.length == 0) {
            return
        }
        val renderRange = mMapRenderRange[t]
        validPosition(renderRange, position)

        // this delete last item -> just remove cluster viewChangeistener
        var length = renderRange!!.length - 1
        if (length < 0) length = 0
        mMapRenderRange[t] = RenderRange(renderRange.position, length)
        position += renderRange.position
        adapter.remove(position)
        calculateMapRenderRange()
    }

    fun replace(t: T, position: Int, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        // first remove old
        var position = position
        val renderRange = mMapRenderRange[t]
        validPosition(renderRange, position)
        position += renderRange!!.position
        adapter.replace(position, item)
    }

    fun replace(t: T, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        val items: MutableList<BaseRecycleViewItem<RecyclerView.ViewHolder>> = ArrayList()
        items.add(item)
        replace(t, items)
    }

    fun replaceAndUpdateIfExisted(t: T, item: BaseRecycleViewItem<RecyclerView.ViewHolder>) {
        if (getLenghtOfCluster(t) > 0) update(t, 0, item) else replace(t, item)
    }

    fun replace(t: T, items: List<BaseRecycleViewItem<RecyclerView.ViewHolder>>) {
        // first remove old
        val renderRange = mMapRenderRange[t]
        if (renderRange!!.length > 0) adapter.remove(renderRange.position, renderRange.length)
        mMapRenderRange[t] = RenderRange(renderRange.position, items.size)
        adapter.append(renderRange.position, items)
        calculateMapRenderRange()
    }

    /**/
    private fun calculateMapRenderRange() {
        var position = 0
        for (t in mClusters) {
            val length = mMapRenderRange[t]!!.length
            mMapRenderRange[t] = RenderRange(position, length)
            position += length
        }
    }

    fun validPosition(renderRange: RenderRange?, position: Int) {
        check(!(position < 0 || position > renderRange!!.length)) {
            String.format(
                "Recycler-DataManager: Out off range: " +
                        "pos %s length %d", position, renderRange!!.length
            )
        }
    }

    fun getStartPosition(t: T): Int {
        var pos = -1
        val renderRange = mMapRenderRange[t]
        if (renderRange != null) pos = renderRange.position
        return pos
    }

//    fun existPosition(cluster: String?, position: Int): Boolean {
//        return mMapRenderRange.get(cluster)!!.length > position
//    }

    fun existPosition(cluster: Any?, position: Int): Boolean {
        return mMapRenderRange[cluster]!!.length > position
    }

//    fun getLenghtOfCluster(cluster: String?): Int {
//        return mMapRenderRange.get(cluster)!!.length
//    }

    fun getLenghtOfCluster(cluster: T): Int {
        return mMapRenderRange[cluster]!!.length
    }

    init {
        mClusters = ArrayList()
        adapter = RecycleViewAdapter()
        mMapRenderRange = HashMap()
    }
}