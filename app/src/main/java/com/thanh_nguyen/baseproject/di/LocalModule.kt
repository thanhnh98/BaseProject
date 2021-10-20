package com.thanh_nguyen.baseproject.di

import android.util.Log
import com.google.android.gms.auth.api.signin.internal.Storage
import com.thanh_nguyen.baseproject.app.data.database.StorageDatabase
import com.thanh_nguyen.baseproject.app.data.database.dao.StorageItemDao
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

const val LOCAL_MODULE = "local_module"

val localModule = Kodein.Module(LOCAL_MODULE, false){
    bind() from singleton {
        Log.e("create DB","OK")
        StorageDatabase(instance())
    }
    bind() from provider {
        createStorageDAO(instance())
    }
}

fun createStorageDAO(db: StorageDatabase): StorageItemDao {
    return db.storageItemDao()
}
