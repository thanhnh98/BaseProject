package com.thanh_nguyen.baseproject.app

import android.app.Application
import com.thanh_nguyen.baseproject.di.appModule
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.login.LoginFacebookManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class AppApplication: Application(), KodeinAware {
    override fun onCreate() {
        super.onCreate()
        LoginFacebookManager.init(this)
        LoginGoogleManager.init(this)
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@AppApplication))
        import(appModule)
    }
}