package com.thanh_nguyen.baseproject.firebase

import android.R.attr
import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class FirebaseManager {
    companion object{
        private lateinit var auth: FirebaseAuth
        private var instance: FirebaseManager? = null
        fun init(){
            auth = FirebaseAuth.getInstance()
        }

        fun getInstance(): FirebaseManager{
            if (instance == null)
                instance = FirebaseManager()
            return instance!!
        }
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }

    fun signUp(email: String, password: String, callbackResult: (Boolean, FirebaseUser?) -> Unit){
        GlobalScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.e( "signUp result", "createUserWithEmail:success")
                        val user = auth.currentUser
                        callbackResult.invoke(true, user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e("signUp result", "createUserWithEmail:failure", task.exception)
                        callbackResult.invoke(false, null)
                    }
                }
        }
    }

    fun signIn(email: String, password: String, callbackResult: (Boolean, FirebaseUser?) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signIn", "signInWithEmail:success")
                    val user = auth.currentUser
                    callbackResult.invoke(true, user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("signIn", "signInWithEmail:failure", task.exception)
                    callbackResult.invoke(false, null)
                }
            }
    }

    fun verifiedPhone(phoneNumber: String, activity: Activity, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}