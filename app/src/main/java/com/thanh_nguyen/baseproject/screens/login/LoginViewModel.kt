package com.thanh_nguyen.baseproject.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseViewModel
import com.thanh_nguyen.baseproject.repo.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepo: LoginRepository): BaseViewModel() {

    override fun onCreate() {
        super.onCreate()
        getAuthorInfo()
    }

    private fun getAuthorInfo(){
        viewModelScope.launch {
            val res = loginRepo.getAuthorInfo()
            Log.e("data", Gson().toJson(res))
        }
    }
}