package com.smb.copticmatch.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
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

        //This can be use in future if any updates needs  when token expired
       /* var data = intent.getStringExtra("token_exp")
        if(data!=null){
            Snackbar.make(findViewById<FrameLayout>(R.id.container), data, Snackbar.LENGTH_LONG).show()
        } else if (intent.getStringExtra("otp") != null&&intent!!.getStringExtra("email")!=null) {

        }*/

        //this for avoid unWanted redirection by using deeplink if link has exist on intent
        if(!intent.getBooleanExtra("omitDeepLink",false)) {
            checkLink()
        }
       // setActivityTheme()
    }
    private fun setActivityTheme() {
        setTheme(R.style.AppTheme)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext,R.color.transparent)))
//        supportActionBar!!.setTitle("")
//        supportActionBar!!.elevation= 0F
        window!!.statusBarColor=resources.getColor(R.color.black_op86)
        window!!.decorView.systemUiVisibility=0

        window!!.setBackgroundDrawableResource(R.drawable.login_background_layer)

    }
    private fun checkLink() {

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    if (pendingDynamicLinkData != null) {

                        val referal_code = pendingDynamicLinkData.link!!.getQueryParameter("referal_code")
                        val vrify_code = pendingDynamicLinkData.link!!.getQueryParameter("token")

                        //val bundle = pendingDynamicLinkData.link
                        //val email = pendingDynamicLinkData.link!!.getQueryParameter("email")

                        if (!vrify_code.isNullOrEmpty()) {
                            // var otpData=Otp(vrify_code!!,email!!)
                            var otpData = Otp(vrify_code , "" )
                            mViewModel.currentPageLiveData.value = otpData

                        }else if(!referal_code.isNullOrEmpty()){
                            SessionUtils.saveReferralCode(referal_code,this@LoginActivity)
                        }
                    }
                }.addOnFailureListener {

         }
    }
}
