package com.thanh_nguyen.baseproject.di

import com.thanh_nguyen.baseproject.app.data.data_source.remote.LoginRemoteDataSource
import com.thanh_nguyen.baseproject.app.data.database.dao.StorageItemDao
import com.thanh_nguyen.baseproject.app.data.repository.LoginRepositoryImpl
import com.thanh_nguyen.baseproject.app.domain.repositories.LoginRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


/**
 * module for repository dependencies
 */

const val REPO_MODULE = "repo_module"

val repositoryModule = Kodein.Module(REPO_MODULE, false){
    bind() from singleton {
        createLoginRepository(instance(), instanceStorageItemDao = instance())
    }
}

fun createLoginRepository(instance: LoginRemoteDataSource, instanceStorageItemDao: StorageItemDao): LoginRepository{
    return LoginRepositoryImpl(instance, instanceStorageItemDao)
}