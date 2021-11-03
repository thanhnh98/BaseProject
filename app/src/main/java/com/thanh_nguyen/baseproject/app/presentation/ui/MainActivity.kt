package com.thanh_nguyen.baseproject.app.presentation.ui

import android.content.Intent
import android.os.Bundle
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.app.presentation.ui.login.LoginActivity
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.databinding.ActivityMainBinding
import com.thanh_nguyen.baseproject.test_dagger.TestDaggerViewModel
import com.thanh_nguyen.baseproject.utils.onClick
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var testDaggerViewModel: TestDaggerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            btnLogin.onClick {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }

    }

    override fun inflateLayout(): Int = R.layout.activity_main
}