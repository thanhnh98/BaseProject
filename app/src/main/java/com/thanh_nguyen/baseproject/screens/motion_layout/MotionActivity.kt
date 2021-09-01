package com.thanh_nguyen.baseproject.screens.motion_layout

import android.os.Bundle
import android.util.Log
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.databinding.ActivityMotionBinding
import com.thanh_nguyen.baseproject.utils.onClick

class MotionActivity: BaseActivity<ActivityMotionBinding>() {
    override fun inflateLayout(): Int = R.layout.activity_motion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.constraintLayout.onClick {
            Log.e("click","motion clicked")
        }
    }

    override fun onBackPressed() {
        binding.constraintLayout.transitionToStart()
    }
}