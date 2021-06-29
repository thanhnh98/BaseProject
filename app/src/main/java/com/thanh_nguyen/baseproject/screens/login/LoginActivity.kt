package com.thanh_nguyen.baseproject.screens.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivityMVVM
import com.thanh_nguyen.baseproject.databinding.ActivityLoginBinding
import com.thanh_nguyen.baseproject.onClick
import com.thanh_nguyen.baseproject.utils.loadImage
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.google.modle.LoginResult
import com.thanh_nguyen.login.LoginFacebookManager
import kodeinViewModel

class LoginActivity: BaseActivityMVVM<ActivityLoginBinding, LoginViewModel>() {
    override fun inflateLayout(): Int = R.layout.activity_login

    override val viewModel: LoginViewModel by kodeinViewModel()

    private var loginFacebookManager = LoginFacebookManager()
    private var loginGoogleManager = LoginGoogleManager()

    override fun onCreateX(savedInstanceState: Bundle?) {
        super.onCreateX(savedInstanceState)
        loginFacebookManager.register(this)
        loginGoogleManager.register(this)
        with(binding){
            flGoogle.onClick{
                loginGoogle()
            }
            flFacebook.onClick {
                loginFacebook()
            }
            flApple.onClick {
                loginApple()
            }
        }
    }

    private fun loginApple() {
        Toast.makeText(this, "Not support", Toast.LENGTH_SHORT).show()
    }

    private fun loginFacebook() {
        loginFacebookManager.loginWithCallback {
            Log.e("success", Gson().toJson(it))
            loggedIn(
                it.avatar?:"",
                it.name?:""
            )
        }
    }

    private fun loginGoogle() {
        loginGoogleManager.login()
        loginGoogleManager.registerListener(object : LoginGoogleManager.GoogleCallbackManager{
            override fun onLoginFailed() {

            }

            override fun onLoginCancelled() {

            }

            override fun onLoginSuccess(result: LoginResult?) {
                loggedIn(result?.avatar?:"", result?.displayName?:"")
            }

            override fun onLogOutSuccess() {
            }
        })
    }

    private fun loggedIn(avatarUrl: String, name: String){
        binding.lnlInput.visibility = View.GONE
        binding.tvName.text = name
        loadImage(avatarUrl, binding.imgAvatar)
        binding.groupInfo.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginGoogleManager.onGoogleSignedInResult(requestCode, resultCode, data)
        loginFacebookManager.registerCallbackManager(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}