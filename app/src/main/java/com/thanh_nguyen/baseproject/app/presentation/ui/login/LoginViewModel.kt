package com.thanh_nguyen.baseproject.app.presentation.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseViewModel
import com.thanh_nguyen.baseproject.app.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase): BaseViewModel() {
    override fun onCreate() {
        super.onCreate()
        getAuthorInfo()
    }

    private fun getAuthorInfo(){
        viewModelScope.launch {
            val res = loginUseCase.getAuthorInfo().collect {
                Log.e("data", Gson().toJson(it))
            }
        }
    }
}