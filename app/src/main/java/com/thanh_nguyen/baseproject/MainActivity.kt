package com.thanh_nguyen.baseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.common.base.adapter.RecyclerManager
import com.thanh_nguyen.baseproject.databinding.ActivityMainBinding
import com.thanh_nguyen.baseproject.network.remote.LoginRemoteDataSource
import com.thanh_nguyen.baseproject.screens.TestViewItem
import com.thanh_nguyen.baseproject.screens.test_item_2.TestItem2ViewItem
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.google.modle.LoginResult
import com.thanh_nguyen.login.LoginFacebookManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
    }

    private val recyclerManager = RecyclerManager<Any>()
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)

        recyclerManager.addCluster(TestItem2ViewItem::class)
        recyclerManager.addCluster(TestViewItem::class)

        with(binding.recyclerView){
            adapter = recyclerManager.adapter
            this.layoutManager = layoutManager
        }

        recyclerManager.replace(TestItem2ViewItem::class, TestItem2ViewItem())
        recyclerManager.replace(TestViewItem::class, listOf(
            TestViewItem(),
            TestViewItem(),
        ))
        recyclerManager.append(TestViewItem::class, listOf(
            TestViewItem(),
            TestViewItem(),
            TestViewItem(),
        ))
    }
}