package com.thanh_nguyen.baseproject.di

import com.thanh_nguyen.baseproject.external.SendBirdSdkHelper
import com.thanh_nguyen.baseproject.external.firebase.FirebaseManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

const val APP_MODULE = "app_module"

val appModule = Kodein.Module(APP_MODULE, false){
    import(serviceModule)
    import(remoteModule)
    import(localModule)
    import(repositoryModule)
    import(viewModelModule)
    import(useCaseModule)

    bind() from singleton {
        FirebaseManager()
    }

    bind() from singleton {
        SendBirdSdkHelper()
    }
}