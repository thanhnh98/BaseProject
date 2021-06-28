package com.thanh_nguyen.baseproject.screens.login

import android.os.Bundle
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivityMVVM
import com.thanh_nguyen.baseproject.databinding.ActivityLoginBinding
import kodeinViewModel

class LoginActivity: BaseActivityMVVM<ActivityLoginBinding, LoginViewModel>() {
    override fun inflateLayout(): Int = R.layout.activity_login

    override val viewModel: LoginViewModel by kodeinViewModel()

    override fun onCreateX(savedInstanceState: Bundle?) {
        super.onCreateX(savedInstanceState)

    }

}