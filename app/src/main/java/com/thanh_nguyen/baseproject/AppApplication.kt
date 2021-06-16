package com.thanh_nguyen.baseproject

import android.app.Application
import com.thanh_nguyen.login.LoginFacebookManager

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        LoginFacebookManager.init(this)
    }
}