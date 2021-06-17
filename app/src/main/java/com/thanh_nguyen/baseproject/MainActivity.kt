package com.thanh_nguyen.baseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.databinding.ActivityMainBinding
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.google.modle.LoginResult
import com.thanh_nguyen.login.LoginFacebookManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val loginFacebookManager = LoginFacebookManager()
    private val loginGoogleManager = LoginGoogleManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginFacebookManager.register(this)
        loginGoogleManager.register(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.loginButton.setOnClickListener {
            loginFacebookManager.loginWithCallback{
                Log.e("succes ?? ", "-> ok ${Gson().toJson(it)}")
            }
        }

        binding.logoutButton.setOnClickListener {
            loginFacebookManager.logout()
        }

        binding.loginButton.setOnClickListener {
            loginFacebookManager.loginWithCallback(){
                Log.e("succes ?? ", "-> ok ${Gson().toJson(it)}")
            }
        }

        loginGoogleManager.registerListener(object : LoginGoogleManager.GoogleCallbackManager{
            override fun onLoginFailed() {

            }

            override fun onLoginCancelled() {
            }

            override fun onLoginSuccess(result: LoginResult?) {
                Log.e("success", "OK -> $result")
            }

            override fun onLogOutSuccess() {
                Log.e("logged out","OK")
            }

        })

        binding.loginButtonGoogle.setOnClickListener {
            loginGoogleManager.login()
        }

        binding.logoutButtonGoogle.setOnClickListener {
            loginGoogleManager.logOut()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginFacebookManager.registerCallbackManager(requestCode, resultCode, data?:Intent())
        loginGoogleManager.onGoogleSignedInResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}