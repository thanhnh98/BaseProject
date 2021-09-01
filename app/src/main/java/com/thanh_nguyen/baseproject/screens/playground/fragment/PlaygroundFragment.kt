package com.thanh_nguyen.baseproject.screens.playground.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.Constants
import com.thanh_nguyen.baseproject.common.base.mvvm.fragment.BaseCollectionFragmentMVVM
import com.thanh_nguyen.baseproject.databinding.FragmentPlaygroundBinding
import com.thanh_nguyen.baseproject.utils.observeLiveDataChanged
import com.thanh_nguyen.baseproject.utils.onClick
import com.thanh_nguyen.baseproject.screens.playground.items.my_message.MyMessageRecycleViewItem
import com.thanh_nguyen.baseproject.screens.playground.items.others_message.OtherMessageRecycleViewItem
import kodeinViewModel

class PlaygroundFragment: BaseCollectionFragmentMVVM<FragmentPlaygroundBinding, PlaygroundViewModel>() {
    companion object{
        fun getInstance(email: String, name: String): PlaygroundFragment{
            return PlaygroundFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.BundleKey.EMAIL, email)
                    putString(Constants.BundleKey.NAME, name)
                }
            }
        }
    }


    private lateinit var name: String
    private lateinit var email: String

    override val viewModel: PlaygroundViewModel by kodeinViewModel()

    override fun inflateLayout(): Int = R.layout.fragment_playground

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString(Constants.BundleKey.NAME)?:"NULL"
        email = arguments?.getString(Constants.BundleKey.EMAIL)?:"NULL"
        adjustPanOnKeyboard()
        viewModel.connect(email)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedX(view, savedInstanceState)
        with(binding){
//            btnConnect.onClick {
//                //sendBird.connect(email)
//                showMyMessage("hahaha msg 1")
//            }
//            btnCreate.onClick {
//                showOtherMessage("hahahaha  msg 2")
//            }
//            btnJoin.onClick {
//                sendBird.joinChannel("sendbird_open_channel_4329_4163c65fcbe0026268660283b01b921add3ebbc7")
//            }
            btnSend.onClick {
                if (edtMsg.text.isNotEmpty()) {
                    viewModel.sendMessage(edtMsg.text.toString().trim())
                    showMyMessage(edtMsg.text.toString().trim())
                    edtMsg.setText("")
                }
            }
        }

        observeLiveDataChanged(viewModel.loadMoreData){
            appendListItem()
        }

        observeLiveDataChanged(viewModel.onMessageReceived()){
            Log.e("received", "${Gson().toJson(it.second)}")
            showOtherMessage(it.second?.message?:return@observeLiveDataChanged)
        }
    }

    override fun initClusters() {
        addCluster(MyMessageRecycleViewItem::class)
    }

    private fun showListItem(){

    }

    private fun appendListItem(){

    }

    private fun showMyMessage(msg: String){
        recyclerView.scrollToPosition(0)
        recyclerManager.replaceAndAppendIfExist(MyMessageRecycleViewItem::class, MyMessageRecycleViewItem(msg), 0)
    }

    private fun showOtherMessage(msg: String){
        recyclerView.scrollToPosition(0)
        recyclerManager.replaceAndAppendIfExist(MyMessageRecycleViewItem::class, OtherMessageRecycleViewItem(msg), 0)
    }

    override fun shouldLoadMore(): Boolean {
        return false
    }

    override fun shouldPullToRefresh(): Boolean {
        return false
    }

    override fun isReverseLayout(): Boolean {
        return true
    }
}