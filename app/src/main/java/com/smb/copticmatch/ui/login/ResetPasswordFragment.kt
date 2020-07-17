package com.smb.copticmatch.ui.login

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors
import com.smb.copticmatch.R
import com.smb.copticmatch.data.api.Status
import com.smb.copticmatch.databinding.FragmentResetPasswordBinding
import com.smb.copticmatch.di.Injectable
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.RetryCallback
import com.smb.copticmatch.utils.CommonUtils
import com.smb.copticmatch.utils.RequestBodyUtil
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule


/*
 * Reset Password Fragment.
 *
 */
class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>(), Injectable {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LoginViewModel

    //navigation controller function
    fun navController() = findNavController()

    private  var mEmail =""
    private  var mOtp =""

    override fun getLayoutId(): Int {
        return R.layout.fragment_reset_password
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = getViewModel(LoginViewModel::class.java)
        mEmail= requireArguments().get("email") as String
        mOtp= requireArguments().get("otp") as String
        setAction()
    }

    private fun setAction() {
        mBinding.imgBack.setOnClickListener {
            activity!!.onBackPressed()
        }
        mBinding.btSave.setOnClickListener {
            CommonUtils.hideKeyboard(mBinding.btSave, context)
            resetPassword()
        }
    }

    //request to resetPassword
    @SuppressLint("SetTextI18n")
    fun resetPassword() {
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retryResetPassword()
            }
        }

        val mNewPassword=mBinding.edtPassword.text.toString()
        val mConfirmPassword=mBinding.edtConfirmPassword.text.toString()

        if (CommonUtils.isEmptyField(context!!,mBinding.edtPassword, "Password",mBinding.txtValidation)) {
            mBinding.txtValidation.text="Enter your new password"
            return
        }
        if (!CommonUtils.isValidPassword(context!!,mBinding.edtPassword, "Password",mBinding.txtValidation)) {
            mBinding.txtValidation.text =getString(R.string.password_validation_msg)
            return
        }
        if (CommonUtils.isEmptyField(context!!,mBinding.edtConfirmPassword, "Confirm password",mBinding.txtValidation)) {
            mBinding.txtValidation.text="Enter your Confirm password"
            return
        }

        if(!mNewPassword.equals(mConfirmPassword,false)){
            mBinding.txtValidation.text="Password does not match"
            mBinding.edtConfirmPassword.background = context!!.getDrawable(R.drawable.shape_text_input_red)
            return
        }
        setObserver()
        val map: HashMap<String, Any> = hashMapOf()
        map["password"] = mNewPassword
        map["email"] = mEmail
        map["otp"] = mOtp
        mViewModel.resetPassword(RequestBodyUtil.getRequestBodyMap(map))
    }

    //observing reset password response
    private fun setObserver() {
        mViewModel.resetPasswordRepositories.removeObservers(this)
        mViewModel.resetPasswordRepositories.observe(this, Observer {

            mBinding.searchResource = it
            if (it == null ||  it.status == Status.LOADING ) {
                return@Observer
            } else {
                mViewModel.resetPasswordNull(RequestBodyUtil.getRequestBodyMap(HashMap()))
                if (it.data!!.status) {
                    showSnackBar(it.data.message)
                    Timer("SettingUp", false).schedule(1000) {
                        navController().navigate(ResetPasswordFragmentDirections.actionLogin())
                    }

                } else {
                    if(!isNetworkConnected(it.status,it.message)){
                        return@Observer
                    }else {
                        CommonUtils.hideKeyboard(mBinding.btSave, context)
                        showSnackBar(it.data!!.message)
                    }
                }

            }
        })
    }





}
