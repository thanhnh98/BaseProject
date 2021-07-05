package com.thanh_nguyen.baseproject.screens.playground.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseCollectionViewModel
import com.thanh_nguyen.baseproject.external.SendBirdSdkHelper
import org.kodein.di.generic.instance

class PlaygroundViewModel(val sendBird: SendBirdSdkHelper): BaseCollectionViewModel() {
    private val _loadMoreData = MutableLiveData<Boolean>().apply { value = false }
    var loadMoreData: LiveData<Boolean> = _loadMoreData

    override fun onCreate() {
        super.onCreate()
    }

    override fun loadMore() {
        super.loadMore()
        _loadMoreData.postValue(true)
        hideLoadingMore()
    }

    fun connect(email: String){
        sendBird.connect(email)
        sendBird.joinChannel("sendbird_open_channel_4329_4163c65fcbe0026268660283b01b921add3ebbc7")
    }

    fun sendMessage(msg: String){
        sendBird.sendMessage(msg){
            Log.e("send success", it.toString())
        }
    }

    fun onMessageReceived() = sendBird.getOnMessageReceivedListener()
}