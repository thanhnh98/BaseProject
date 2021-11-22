package com.thanh_nguyen.baseproject

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleObserver
import com.thanh_nguyen.baseproject.di.appModule
import com.thanh_nguyen.baseproject.external.SendBirdSdkHelper
import com.thanh_nguyen.baseproject.external.firebase.AppFirebaseMessageService
import com.thanh_nguyen.baseproject.external.firebase.FirebaseManager
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.login.LoginFacebookManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class App: Application(), LifecycleObserver, KodeinAware {
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppFirebaseMessageService.init(this)
        FirebaseManager.init()
        LoginFacebookManager.init(this)
        LoginGoogleManager.init(this)
        SendBirdSdkHelper.init(this)

    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))
        import(appModule)
    }

    companion object{
        @Volatile
        private var instance: App? = null

        @JvmStatic
        fun getInstance(): App = instance ?: synchronized(this) {
            instance ?: App().also {
                instance = it
            }
        }

        fun getString(@StringRes strId: Int): String {
            return getResources().getString(strId)
        }

        fun getResources(): Resources {
            return getInstance().resources
        }
    }
}