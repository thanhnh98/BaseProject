package com.thanh_nguyen.baseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.databinding.ActivityMainBinding
import com.thanh_nguyen.login.LoginFacebookManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val loginFacebookManager = LoginFacebookManager.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.loginButton.setOnClickListener {
            loginFacebookManager.loginWithCallback(this){
                Log.e("succes ?? ", "-> ok ${Gson().toJson(it)}")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginFacebookManager.registerCallbackManager(requestCode, resultCode, data?:Intent())
        super.onActivityResult(requestCode, resultCode, data)
    }
}