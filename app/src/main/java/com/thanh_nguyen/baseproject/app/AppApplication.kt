package com.thanh_nguyen.baseproject.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.thanh_nguyen.baseproject.di.appModule
import com.thanh_nguyen.baseproject.di.remoteModule
import com.thanh_nguyen.baseproject.di.serviceModule
import com.thanh_nguyen.baseproject.firebase.AppFirebaseMessageService
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.login.LoginFacebookManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class AppApplication: Application(), LifecycleObserver, KodeinAware {
    override fun onCreate() {
        super.onCreate()
        context = this
        AppFirebaseMessageService.init(this)
        LoginFacebookManager.init(this)
        LoginGoogleManager.init(this)
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@AppApplication))
        import(appModule)
    }

    companion object{
        private var context: Context? = null
        fun getContext(): Context{
            return context!!
        }
    }
}