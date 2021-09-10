package com.thanh_nguyen.baseproject.screens.cgv_clone.item

import android.view.ViewGroup
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.adapter.BindingRecycleViewItem
import com.thanh_nguyen.baseproject.databinding.ItemMovieBinding
import com.thanh_nguyen.baseproject.utils.createViewHolder

class MovieItemRecycleView: BindingRecycleViewItem<ItemMovieBinding, MovieItemRecycleVH>() {

    override fun inflateViewHolder(parent: ViewGroup): MovieItemRecycleVH {
        return parent.createViewHolder(R.layout.item_movie){
            MovieItemRecycleVH(it)
        }
    }

    override fun bindModel(binding: ItemMovieBinding?, viewHolder: MovieItemRecycleVH) {

    }
}