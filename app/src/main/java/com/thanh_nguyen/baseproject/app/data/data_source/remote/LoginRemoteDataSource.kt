package com.thanh_nguyen.baseproject.app.data.data_source.remote

import android.util.Log
import com.thanh_nguyen.baseproject.app.data.service.LoginService

class LoginRemoteDataSource(private val loginService: LoginService): BaseRemoteDataSource() {
    fun getAuthorInfo() = getResult {
        Log.e("calling author", "in dât source")
        loginService.getAuthorInfo()
    }
}