package com.thanh_nguyen.baseproject.screens.playground.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseCollectionViewModel

class PlaygroundViewModel: BaseCollectionViewModel() {
    private val _loadMoreData = MutableLiveData<Boolean>().apply { value = false }
    var loadMoreData: LiveData<Boolean> = _loadMoreData

    override fun loadMore() {
        super.loadMore()
        _loadMoreData.postValue(true)
    }
}