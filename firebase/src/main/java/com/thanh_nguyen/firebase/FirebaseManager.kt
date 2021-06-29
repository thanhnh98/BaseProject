package com.thanh_nguyen.firebase

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class FirebaseManager {
    companion object{
        private lateinit var auth: FirebaseAuth
        fun init(context: Context){
            auth = FirebaseAuth.getInstance()
        }
    }

    fun loginTest(){}
}