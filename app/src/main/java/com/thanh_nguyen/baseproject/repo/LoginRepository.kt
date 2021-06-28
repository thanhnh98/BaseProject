package com.thanh_nguyen.baseproject.repo

import com.thanh_nguyen.baseproject.network.remote.LoginRemoteDataSource

class LoginRepository(private val dataSource: LoginRemoteDataSource) {
    suspend fun getAuthorInfo() = dataSource.getAuthorInfo()
}