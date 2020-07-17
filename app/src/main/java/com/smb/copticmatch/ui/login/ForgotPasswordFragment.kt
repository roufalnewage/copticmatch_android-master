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
import com.smb.copticmatch.databinding.FragmentForgotPasswordBinding
import com.smb.copticmatch.di.Injectable
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.RetryCallback
import com.smb.copticmatch.utils.CommonUtils
import com.smb.copticmatch.utils.NetworkUtils.isNetworkConnected
import com.smb.copticmatch.utils.RequestBodyUtil
import java.util.*
import javax.inject.Inject



/**
 * Forgot Password Fragment.
 *
 */
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(), Injectable {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LoginViewModel

    //navigation controller function
    fun navController() = findNavController()

    override fun getLayoutId(): Int {
        return R.layout.fragment_forgot_password
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = getViewModel(LoginViewModel::class.java)

        setActions()
    }

    //setting user actions
    private fun setActions() {
        mBinding.imgBack.setOnClickListener {
            activity!!.onBackPressed()
        }
        mBinding.btSendEmail.setOnClickListener {
            sentOtp()
        }
    }

    //request to send otp
    @SuppressLint("SetTextI18n")
    fun sentOtp() {
        CommonUtils.hideKeyboard(mBinding.btSendEmail, context)
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retrySendOtp()
            }
        }
        if (!CommonUtils.isEmailValid(mBinding.edtEmail,context!!,mBinding.txtValidation)) {
            if (TextUtils.isEmpty(mBinding.edtEmail.text.toString())) {
                mBinding.txtValidation.text="Enter your registered email"
            }else{
                mBinding.txtValidation.text="Enter a valid email"
            }
            return
        }
        setObserver()
        val map: HashMap<String, Any> = hashMapOf()
        map["email"] = mBinding.edtEmail.text.toString()
        mViewModel.sendOtp(RequestBodyUtil.getRequestBodyMap(map))
    }
    //observing otp sending response
    private fun setObserver() {
        mViewModel.sendOtpRepositories.removeObservers(this)
        mViewModel.sendOtpRepositories.observe(this, Observer {
            mBinding.searchResource = it
            if (it == null ||  it.status == Status.LOADING ) {
                return@Observer
            } else {
                mViewModel.sendOtpNull(RequestBodyUtil.getRequestBodyMap(HashMap()))
                if (it.data!=null&& it.data.status) {
                    showSnackBar(it.data!!.message)
                    navController().navigate(ForgotPasswordFragmentDirections.actionOtpVerification(mBinding.edtEmail.text.toString(),""))
                } else {
                    if(!isNetworkConnected(it.status,it.message)){
                        return@Observer
                    }else {
                        CommonUtils.hideKeyboard(mBinding.btSendEmail, context)
                        showSnackBar(it.data!!.message)
                    }
                }

            }
        })
    }


}
