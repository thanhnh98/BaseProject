package com.thanh_nguyen.baseproject.app.presentation.ui.login

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseViewModel
import com.thanh_nguyen.baseproject.app.domain.usecases.LoginUseCase
import com.thanh_nguyen.baseproject.app.model.AuthorModel
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity
import com.thanh_nguyen.baseproject.app.model.respone.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class LoginViewModel(private val loginUseCase: LoginUseCase): BaseViewModel() {

    override fun onCreate() {
        super.onCreate()
        getAuthorInfo()
    }

    fun getAuthorInfo(){
        viewModelScope.launch {
            loginUseCase.getAuthorInfo().collect {
                Log.e("data", Gson().toJson(it))
            }
        }
    }
}