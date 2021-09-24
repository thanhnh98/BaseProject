package com.thanh_nguyen.baseproject.app.data.repository

import com.thanh_nguyen.baseproject.app.data.data_source.remote.LoginRemoteDataSource
import com.thanh_nguyen.baseproject.app.domain.repositories.LoginRepository

class LoginRepositoryImpl(private val dataSource: LoginRemoteDataSource): LoginRepository {
    override fun getAuthorInfo() = dataSource.getAuthorInfo()
}