package com.smb.copticmatch.ui.login

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors
import com.smb.copticmatch.R
import com.smb.copticmatch.data.api.Status
import com.smb.copticmatch.di.Injectable
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.RetryCallback
import com.smb.copticmatch.utils.RequestBodyUtil
import java.util.*
import javax.inject.Inject
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.smb.copticmatch.databinding.FragmentOtpVerificationBinding
import com.smb.copticmatch.utils.CommonUtils


/**
 * Forgot Password Fragment.
 *
 */
class OtpVerificationFragment : BaseFragment<FragmentOtpVerificationBinding>(), Injectable {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LoginViewModel
    var allowRefresh = false

    //navigation controller function
    fun navController() = findNavController()

    private var mEmail = ""

    override fun getLayoutId(): Int {
        return R.layout.fragment_otp_verification
    }

    override fun onStart() {
        super.onStart()


    }

    override fun onResume() {
        super.onResume()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = getViewModel(LoginViewModel::class.java)
        if (arguments?.get("email") != null)
            mEmail = arguments?.get("email") as String
        setActions()
        setSubText()
    }

    //setting top description text with email using spannable string
    private fun setSubText() {
        val word = SpannableString(resources.getString(R.string.otp_text))
        word.setSpan(ForegroundColorSpan(ContextCompat.getColor(context!!, R.color.colorPrimaryDark)),
                0, word.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

       // mBinding.txtOtp.text = word
       /* val wordTwo = SpannableString("\t" + fromHtml("<b> $mEmail</b> "))

        wordTwo.setSpan(ForegroundColorSpan(ContextCompat.getColor(context!!, R.color.card_blue)),
                0, wordTwo.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        mBinding.txtOtp.append(wordTwo)*/
    }

    //setting user actions
    private fun setActions() {
        mBinding.imgBack.setOnClickListener {
            activity!!.onBackPressed()
        }
        mBinding.btSendEmail.setOnClickListener {
            verifyOtp()
        }
        mBinding.txtResendOtp.setOnClickListener {
            resendOtp()
        }

    }

    //request to verify otp
    @SuppressLint("SetTextI18n")
    fun verifyOtp() {
        CommonUtils.hideKeyboard(mBinding.btSendEmail, context)
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retrySendOtp()
            }
        }

        if (TextUtils.isEmpty(mBinding.pinView.text.toString())) {
            mBinding.txtValidation.text = "Enter your otp"
        }

        setVerifyObserver()
        val map: HashMap<String, Any> = hashMapOf()
        map["otp"] = mBinding.pinView.text.toString()
        map["email"] = mEmail
        mViewModel.verifyOtp(RequestBodyUtil.getRequestBodyMap(map))
    }

    //observing otp verify response
    private fun setVerifyObserver() {
        mViewModel.verifyOtpRepositories.removeObservers(this)
        mViewModel.verifyOtpRepositories.observe(this, Observer {
            mBinding.searchResource = it
            if (it == null || it.status == Status.LOADING) {
                return@Observer
            } else {
                mViewModel.verifyOtpNull(RequestBodyUtil.getRequestBodyMap(HashMap()))
                if (it.data != null && it.data.status) {
                    showSnackBar(it.data.message)
                    navController().navigate(OtpVerificationFragmentDirections.actionResetPassword(mEmail,mBinding.pinView.text.toString()))
                } else {
                    if (!isNetworkConnected(it.status, it.message)) {
                        return@Observer
                    } else {
                        CommonUtils.hideKeyboard(mBinding.btSendEmail, context)
                        showSnackBar(it.data!!.message)
                    }
                }

            }
        })
    }

    //request to send otp
    @SuppressLint("SetTextI18n")
    fun resendOtp() {
        CommonUtils.hideKeyboard(mBinding.btSendEmail, context)
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retrySendOtp()
            }
        }

        setResendObserver()
        val map: HashMap<String, Any> = hashMapOf()
        if (!mEmail.isEmpty()) {
            map["email"] = mEmail
        } else if (arguments?.get("token") != null) {
            map["email"] = arguments?.get("token").toString()
        }

        mViewModel.sendOtp(RequestBodyUtil.getRequestBodyMap(map))
    }

    //observing otp sending response
    private fun setResendObserver() {
        mViewModel.sendOtpRepositories.removeObservers(this)
        mViewModel.sendOtpRepositories.observe(this, Observer {
            mBinding.searchResource = it
            if (it == null || it.status == Status.LOADING) {
                return@Observer
            } else {
                mViewModel.sendOtpNull(RequestBodyUtil.getRequestBodyMap(HashMap()))
                if (it.data != null && it.data!!.status) {
                    showSnackBar(it.data!!.message)
                } else {
                    if (!isNetworkConnected(it.status, it.message)) {
                        return@Observer
                    } else {
                        CommonUtils.hideKeyboard(mBinding.btSendEmail, context)
                        showSnackBar(it.data!!.message)
                    }
                }

            }
        })
    }


}
