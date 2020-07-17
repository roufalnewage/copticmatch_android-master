package com.smb.copticmatch.ui.home

import android.os.Bundle
import com.smb.copticmatch.databinding.ActivityHomeBinding
import com.smb.copticmatch.databinding.ActivityLoginBinding
import com.smb.copticmatch.ui.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityHomeBinding.inflate(layoutInflater).root)
    }
}