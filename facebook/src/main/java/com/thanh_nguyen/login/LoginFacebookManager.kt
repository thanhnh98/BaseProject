package com.thanh_nguyen.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.thanh_nguyen.model.FacebookLoginResult

@SuppressLint("StaticFieldLeak")
class LoginFacebookManager {
    private val loginManager = LoginManager.getInstance()
    private val callbackManager = CallbackManager.Factory.create()

    companion object{
        fun init(context: Context){
            FacebookSdk.sdkInitialize(context)
            AppEventsLogger.activateApp(context)
        }

        fun getInstance(): LoginFacebookManager{
            return LoginFacebookManager()
        }
    }

    fun login(context: Activity){
        loginManager.logInWithReadPermissions(
            context,
            listOf(
                "public_profile"
            )
        )
    }

    fun loginWithCallback(context: Activity, callback: (FacebookLoginResult) -> Unit){
        loginManager.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                    val request = GraphRequest.newMeRequest(
                        result?.accessToken
                    ){ obj, res ->
                        val email = obj.getString("email")
                        val name = obj.getString("name")
                        val id = obj.getString("id")
                        callback.invoke(FacebookLoginResult(
                            result?.accessToken?.token,
                            email = email,
                            name = name,
                            id = id,
                        ))
                    }

                    val parameters = Bundle()
                    parameters.putString("fields", "name,email,id")
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {
                    Log.e("login facebook", "onCancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.e("login facebook", "onError")

                }
            }
        )
        login(context)
    }

    fun registerCallbackManager(requestCode: Int, resultCode: Int, data: Intent): Boolean{
        return callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}