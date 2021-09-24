package com.thanh_nguyen.baseproject.app.presentation.ui.playground

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.Constants
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.databinding.ActivityPlaygroundBinding
import com.thanh_nguyen.baseproject.app.presentation.ui.playground.fragment.PlaygroundFragment

class PlaygroundActivity: BaseActivity<ActivityPlaygroundBinding>() {

    companion object{
        fun getInstance(from: Context, email: String, name: String): Intent{
            return Intent(from, PlaygroundActivity::class.java).apply {
                putExtra(Constants.BundleKey.EMAIL, email)
                putExtra(Constants.BundleKey.NAME, name)
            }
        }
    }

    override fun inflateLayout(): Int = R.layout.activity_playground

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val email = intent.extras?.getString(Constants.BundleKey.EMAIL)
        val name = intent.extras?.getString(Constants.BundleKey.NAME)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragments, PlaygroundFragment.getInstance(email?:return, name?:return))
            .commit()
    }

    override fun onBackPressed() {
        finish()
    }
}