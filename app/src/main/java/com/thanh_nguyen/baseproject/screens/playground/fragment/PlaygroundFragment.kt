package com.thanh_nguyen.baseproject.screens.playground.fragment

import android.os.Bundle
import android.view.View
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.Constants
import com.thanh_nguyen.baseproject.common.base.mvvm.fragment.BaseCollectionFragmentMVVM
import com.thanh_nguyen.baseproject.databinding.FragmentPlaygroundBinding
import com.thanh_nguyen.baseproject.external.SendBirdSdkHelper
import com.thanh_nguyen.baseproject.observeLiveDataChanged
import com.thanh_nguyen.baseproject.onClick
import com.thanh_nguyen.baseproject.screens.TestViewItem
import com.thanh_nguyen.baseproject.screens.test_item_2.TestItem2ViewItem
import kodeinViewModel
import org.kodein.di.generic.instance

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

    private val sendBird: SendBirdSdkHelper by instance()

    private lateinit var name: String
    private lateinit var email: String

    override val viewModel: PlaygroundViewModel by kodeinViewModel()

    override fun inflateLayout(): Int = R.layout.fragment_playground

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString(Constants.BundleKey.NAME)?:"NULL"
        email = arguments?.getString(Constants.BundleKey.EMAIL)?:"NULL"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedX(view, savedInstanceState)
        with(binding){
            btnConnect.onClick {
                //sendBird.connect(email)
                showListItem()
            }
            btnCreate.onClick {
                appendListItem()
            }
//            btnJoin.onClick {
//                sendBird.joinChannel("sendbird_open_channel_4329_4163c65fcbe0026268660283b01b921add3ebbc7")
//            }
//            btnSend.onClick {
//                sendBird.sendMessage(edtMsg.text.toString())
//            }
        }

        observeLiveDataChanged(viewModel.loadMoreData){
            appendListItem()
        }
    }

    override fun initClusters() {
        addCluster(TestItem2ViewItem::class)
        addCluster(TestViewItem::class)
    }

    fun showListItem(){
        recyclerManager.replace(TestItem2ViewItem::class, listOf(
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
        ))
    }

    fun appendListItem(){
        recyclerManager.append(TestViewItem::class, listOf(
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
        ))
    }
}