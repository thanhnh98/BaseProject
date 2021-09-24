package com.thanh_nguyen.baseproject.app.presentation.ui.youtube

import android.os.Bundle
import com.thanh_nguyen.baseproject.R
import com.thanh_nguyen.baseproject.common.base.mvvm.activity.BaseActivity
import com.thanh_nguyen.baseproject.databinding.YoutubeBinding

class YoutubeActivity: BaseActivity<YoutubeBinding>() {
    override fun inflateLayout(): Int = R.layout.youtube
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}