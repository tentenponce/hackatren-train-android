package com.nasaanka.train.ui.main

import android.os.Bundle
import com.nasaanka.train.R
import com.nasaanka.train.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent()?.inject(this)
    }
}
