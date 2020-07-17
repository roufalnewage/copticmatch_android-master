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
import com.smb.copticmatch.databinding.FragmentSignupBinding
import com.smb.copticmatch.di.Injectable
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.RetryCallback
import com.smb.copticmatch.utils.AppConstants
import com.smb.copticmatch.utils.CommonUtils
import com.smb.copticmatch.utils.RequestBodyUtil
import com.smb.copticmatch.utils.SessionUtils
import javax.inject.Inject


/**
 *  Sign up Fragment class.
 */


class SignupFragment : BaseFragment<FragmentSignupBinding>(), Injectable {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LoginViewModel

    //navigation controller function
    fun navController() = findNavController()


    override fun getLayoutId(): Int {
        return R.layout.fragment_signup
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = getViewModel(LoginViewModel::class.java)
        setAction()

    }

    //setting user actions
    private fun setAction() {

        mBinding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        mBinding.btContinue.setOnClickListener {
            CommonUtils.hideKeyboard(mBinding.btContinue, context)
            signUp()
        }
/*
        txtTermsAndPrivacy.setOnClickListener {

        }*/

    }


    //sign up function
    @SuppressLint("SetTextI18n")
    private fun signUp() {
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retrysignUp()
            }
        }
        if (!CommonUtils.isNameValid(requireContext(), mBinding.edtFirstName, "First Name", mBinding.txtValidation)) {
            if (TextUtils.isEmpty(mBinding.edtFirstName.text.toString())) {
                mBinding.txtValidation.text = "Enter  name"
            } else {
                mBinding.txtValidation.text = "Enter valid name"
            }
            return
        }

        if (!CommonUtils.isEmailValid(mBinding.edtEmail, requireContext(), mBinding.txtValidation)) {
            if (TextUtils.isEmpty(mBinding.edtEmail.text.toString())) {
                mBinding.txtValidation.text = "Enter email"
            } else {
                mBinding.txtValidation.text = "Enter a valid email"
            }
            return
        }
        if (!CommonUtils.isValidPassword(requireContext(), mBinding.edtPassword, "Password", mBinding.txtValidation)) {
            mBinding.txtValidation.text = getString(R.string.password_validation_msg)
            return
        }
        if (!CommonUtils.isValidPassword(requireContext(), mBinding.edtConfirmPassword, "Confirm Password", mBinding.txtValidation)) {
            mBinding.txtValidation.text = "Enter confirm password"
            return
        }
        if (!mBinding.edtPassword.text.toString().equals(mBinding.edtConfirmPassword.text.toString(), false)) {
            mBinding.txtValidation.text = "Password mismatch"
            mBinding.edtConfirmPassword.background = requireContext().getDrawable(R.drawable.shape_text_input_red)
            return
        }
        setObserver()
        val map: HashMap<String, Any> = hashMapOf()
        map["first_name"] = mBinding.edtFirstName.text.toString().trim()
        map["last_name"] = ""
        map["email"] = mBinding.edtEmail.text.toString().trim()
        map["password"] = mBinding.edtPassword.text.toString().trim()
        map["platform"] = AppConstants.PLATFORM
        SessionUtils.getReferralCode()?.let {
            map["referal_code"] = it
        }

        mViewModel.signUp(RequestBodyUtil.getRequestBodyMap(map))

    }

    //sign up response observing function
    private fun setObserver() {
        mViewModel.signUpRepositories.removeObservers(this)
        mViewModel.signUpRepositories.observe(viewLifecycleOwner, Observer {
            mBinding.searchResource = it
            if (it == null || it.status == Status.LOADING) {
                return@Observer
            } else {
                mViewModel.saveSignUpNull(RequestBodyUtil.getRequestBodyMap(java.util.HashMap()))
                if (it.data != null && it.data.status) {
                    SessionUtils.saveSession(it.data.data, context)
                  //  navController().navigate(SignupFragmentDirections.actionSignUpFragmentToIntroFragment(mBinding.edtFirstName.text.toString().trim()))

                } else {
                    if (!isNetworkConnected(it.status, it.message)) {
                        return@Observer
                    } else {
                        showSnackBar(it.data!!.message)
                    }
                }

            }
        })
    }





}
