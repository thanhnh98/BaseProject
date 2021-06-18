package com.thanh_nguyen.baseproject.network.remote

import com.thanh_nguyen.baseproject.network.ApiClient
import com.thanh_nguyen.baseproject.network.BaseRemoteDataSource
import com.thanh_nguyen.baseproject.network.service.AppService

class AppRemoteDataSource: BaseRemoteDataSource() {
    val service by lazy { ApiClient.createService<AppService>() }

    suspend fun getAuthorInfo() = getResult {
        service.getAuthorInfo()
    }
}