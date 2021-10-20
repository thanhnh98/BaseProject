package com.thanh_nguyen.baseproject.app.presentation.ui.login

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.common.base.mvvm.viewmodel.BaseViewModel
import com.thanh_nguyen.baseproject.app.domain.usecases.LoginUseCase
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class LoginViewModel(private val loginUseCase: LoginUseCase): BaseViewModel() {
    override fun onCreate() {
        super.onCreate()
        getAuthorInfo()
    }

    private fun getAuthorInfo(){

        viewModelScope.launch {

            Log.e("insert", "inserting")

            loginUseCase.insertItem(
                StorageItemEntity(
                    1,
                    "Thanh nè ${System.currentTimeMillis()}",
                    Random(10).nextInt().toString()
                )
            ).apply {
                Log.e("insert", "inserted $this")
            }
            Log.e("insert", "finished")

            val res = loginUseCase.getAuthorInfo().collect {
                Log.e("data", Gson().toJson(it))
            }

            val items = loginUseCase.getAllItems().collect {
                Log.e("data nè", "-> ${Gson().toJson(it)} ")
            }
        }
    }
}