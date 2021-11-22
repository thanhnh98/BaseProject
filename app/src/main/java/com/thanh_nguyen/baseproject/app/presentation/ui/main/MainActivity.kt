package com.thanh_nguyen.baseproject.app.presentation.ui.main

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.app.presentation.ui.main.home.HomeFragment
import com.thanh_nguyen.baseproject.app.presentation.ui.main.profile.ProfileFragment
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val fragments = listOf(
            MainStateModel(
                "Profile",
                R.drawable.ic_apple,
                ProfileFragment()
            ),
            MainStateModel(
                "Home",
                R.drawable.ic_apple,
                HomeFragment()
            ),
        )
        val adapter = MainStateAdapter(this, fragments)

        binding.vpMain.adapter = adapter
        binding.vpMain.isUserInputEnabled = false
        TabLayoutMediator(
            binding.tabMain,
            binding.vpMain
        ){ tab, pos ->
            tab.icon = ResourcesCompat.getDrawable(resources, fragments[pos].icon, null)
            tab.text = fragments[pos].title
        }.attach()
    }

    override fun inflateLayout(): Int = R.layout.activity_main

    override fun onBackPressed() {
        finish()
    }
}