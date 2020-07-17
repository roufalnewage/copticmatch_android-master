package com.smb.copticmatch.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.smb.copticmatch.R
import com.smb.copticmatch.data.model.Otp
import com.smb.copticmatch.ui.BaseActivity
import com.smb.copticmatch.utils.SessionUtils
import javax.inject.Inject


class LoginActivity : BaseActivity() {

    enum class LoginCurrentPage {
        NONE, OTP
    }

    @Inject
    protected lateinit var mViewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(LoginViewModel::class.java)
        setActivityTheme()
    }
    private fun setActivityTheme() {
        setTheme(R.style.AppTheme)
        window!!.statusBarColor=resources.getColor(R.color.black_op86)
        window!!.decorView.systemUiVisibility=0
        window!!.setBackgroundDrawableResource(R.drawable.splash_bg)

    }
}
