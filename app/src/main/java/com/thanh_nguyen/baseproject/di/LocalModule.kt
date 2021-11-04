package com.thanh_nguyen.baseproject.di

import android.util.Log
import com.thanh_nguyen.baseproject.app.data.local_data.data_store.PreferenceDataStore
import com.thanh_nguyen.baseproject.app.data.local_data.room_db.StorageDatabase
import com.thanh_nguyen.baseproject.app.data.local_data.room_db.dao.StorageItemDao
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
    bind() from singleton {
        PreferenceDataStore(instance())
    }
}

fun createStorageDAO(db: StorageDatabase): StorageItemDao {
    return db.storageItemDao()
}
