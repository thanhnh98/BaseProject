package com.thanh_nguyen.baseproject.network.remote

import com.thanh_nguyen.baseproject.network.ApiClient
import com.thanh_nguyen.baseproject.repo.BaseRemoteDataSource
import com.thanh_nguyen.baseproject.repo.service.AppService

class AppRemoteDataSource: BaseRemoteDataSource() {
    private val service by lazy { ApiClient.createService<AppService>() }

    suspend fun getAuthorInfo() = getResult {
        service.getAuthorInfo()
    }
}