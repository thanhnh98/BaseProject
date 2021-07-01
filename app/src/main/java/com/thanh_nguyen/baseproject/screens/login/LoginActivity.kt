package com.thanh_nguyen.baseproject.screens.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivityMVVM
import com.thanh_nguyen.baseproject.databinding.ActivityLoginBinding
import com.thanh_nguyen.baseproject.firebase.FirebaseManager
import com.thanh_nguyen.baseproject.onClick
import com.thanh_nguyen.baseproject.receiver.SmsReceiver
import com.thanh_nguyen.baseproject.screens.playground.PlaygroundActivity
import com.thanh_nguyen.baseproject.showMessage
import com.thanh_nguyen.baseproject.utils.loadImage
import com.thanh_nguyen.google.login.LoginGoogleManager
import com.thanh_nguyen.google.modle.LoginResult
import com.thanh_nguyen.login.LoginFacebookManager
import kodeinViewModel
import org.kodein.di.generic.instance


class LoginActivity: BaseActivityMVVM<ActivityLoginBinding, LoginViewModel>() {
    override fun inflateLayout(): Int = R.layout.activity_login

    override val viewModel: LoginViewModel by kodeinViewModel()

    private var loginFacebookManager = LoginFacebookManager()
    private var loginGoogleManager = LoginGoogleManager()

    private val firebaseManager: FirebaseManager by instance()

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
                firebaseManager.verifiedPhone("+84366234132", this@LoginActivity,
                object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        Log.e("onVerificationCompleted", "onVerificationCompleted:" + credential)
                        showMessage("phone verified succeed")
                    }

                    override fun onVerificationFailed(exp: FirebaseException) {
                        Log.e("onVerificationFailed", "onVerificationFailed: ${exp.message}");
                        showMessage("phone verified failed")
                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(p0, p1)
                        Log.e("onCodeSent", "onCodeSent:" + p0)
                    }

                })
            }
            fabEnter.onClick {
                //AppFirebaseMessageService.getToken()
                if (edtEmail.text.isNullOrEmpty() || edtPassword.text.isNullOrEmpty()){
                    showMessage("Không được bỏ trống email/password")
                    return@onClick
                }
                firebaseManager.signIn(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                ){ isSuccess, userData ->
                    if (isSuccess)
                        loggedIn(userData?.photoUrl.toString(), userData?.email?:"NULL", userData?.displayName?:userData?.email?:"NULL")
                    else
                        showMessage("Đăng nhập thất bại, kiểm tra lại hoặc đăng ký mới")
                }
            }
            tvSignup.onClick {
                if (edtEmail.text.isNullOrEmpty() || edtPassword.text.isNullOrEmpty()){
                    showMessage("Không được bỏ trống email/password")
                    return@onClick
                }
                firebaseManager.signUp(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                ){ isSuccess, userData ->
                    if (isSuccess)
                        showMessage("Đăng ký thành công, bấm nút mũi tên để đăng nhập")
                    else
                        showMessage("Đăng ký thất bại, kiểm tra lại email/password")
                }
            }
        }
        SmsReceiver.onSmsReceived.observe(this){
            showMessage("from ${it.first}: ${it.second}")
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
                it.email?:"",
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
                loggedIn(result?.avatar?:"", result?.email?:"", result?.displayName?:"")
            }

            override fun onLogOutSuccess() {
            }
        })
    }

    private fun loggedIn(avatarUrl: String, email: String, name: String){
        binding.lnlInput.visibility = View.GONE
        binding.tvName.text = name
        loadImage(avatarUrl, binding.imgAvatar)
        binding.groupInfo.visibility = View.VISIBLE
        startActivity(PlaygroundActivity.getInstance(
            this,
            email,
            name
        ))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginGoogleManager.onGoogleSignedInResult(requestCode, resultCode, data)
        loginFacebookManager.registerCallbackManager(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}