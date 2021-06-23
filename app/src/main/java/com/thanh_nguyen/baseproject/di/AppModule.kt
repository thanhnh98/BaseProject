package com.thanh_nguyen.baseproject.di

import org.kodein.di.Kodein

const val APP_MODULE = "app_module"

val appModule = Kodein.Module(APP_MODULE, false){
    import(remoteModule)
    import(repositoryModule)
    import(viewModelModule)
}