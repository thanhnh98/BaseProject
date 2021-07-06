package com.thanh_nguyen.baseproject

import android.content.Intent
import android.os.Bundle
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.databinding.ActivityMainBinding
import com.thanh_nguyen.baseproject.screens.login.LoginActivity
import com.thanh_nguyen.baseproject.screens.motion_layout.MotionActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            btnLogin.onClick {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
            btnOther.onClick {
                startActivity(Intent(this@MainActivity, MotionActivity::class.java))
            }
        }
    }

    override fun inflateLayout(): Int = R.layout.activity_main
}