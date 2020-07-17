package com.smb.copticmatch.ui.login

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors
import com.smb.copticmatch.R
import com.smb.copticmatch.data.api.Status
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.di.Injectable
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.RetryCallback
import com.smb.copticmatch.ui.login.ForgotPasswordFragmentDirections.actionOtpVerification
import com.smb.copticmatch.utils.CommonUtils
import com.smb.copticmatch.utils.CommonUtils.hideKeyboard
import com.smb.copticmatch.utils.RequestBodyUtil
import com.smb.copticmatch.utils.SessionUtils
import java.util.*
import javax.inject.Inject


const val TAG: String = "LoginFragment"

/*
 * Login fragment.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>(), Injectable {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LoginViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    //navigation controller function
    fun navController() = findNavController()



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(requireActivity(), mViewModelFactory).get(LoginViewModel::class.java)
        setAction()
    }


    //setting user actions
    private fun setAction() {
        mBinding.edtPassword.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(mBinding.edtPassword, context)
                    login()
                    return true
                }
                return false
            }
        })

        mBinding.liSignup.setOnClickListener {
            navController().navigate(LoginFragmentDirections.actionSignUp())
        }


        mBinding.txtForgotPassword.setOnClickListener {
            navController().navigate(LoginFragmentDirections.actionForgotPassword())
        }

        mBinding.btLogin.setOnClickListener {
            hideKeyboard(mBinding.btLogin, context)
            login()
        }

        mBinding.containerLayout.setOnClickListener {
            hideKeyboard(mBinding.btLogin, context)
        }

    }

    /*START LOGIN WEB SERVICE*/

    //normal ic_login_background using email and password
    @SuppressLint("SetTextI18n")
    fun login() {
        val email = mBinding.edtEmail.text.toString()
        val password = mBinding.edtPassword.text.toString()
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            return
        }
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retry()
            }
        }
        if (!CommonUtils.isEmailValid(mBinding.edtEmail, requireContext(),  mBinding.txtValidation)) {
            if (TextUtils.isEmpty(email)) {
                mBinding.txtValidation.text = "Enter your email"
            } else {
                mBinding.txtValidation.text = "Enter a valid email"
            }
            return
        }
        if (CommonUtils.isEmptyField(requireContext(), mBinding.edtPassword, "Password",  mBinding.txtValidation)) {
            mBinding.txtValidation.text = "Enter your password"
            return
        }
        setObserver()
        val map: HashMap<String, Any> = hashMapOf()
        map["email"] = email
        map["password"] = password
        mViewModel.login(RequestBodyUtil.getRequestBodyMap(map))
    }


    //setting observer for ic_login_background response
    private fun setObserver() {
        mViewModel.loginRepositories.removeObservers(this)
        mViewModel.loginRepositories.observe(viewLifecycleOwner, Observer {
            mBinding.searchResource = it
            if (it == null || it.status == Status.LOADING) {
                return@Observer
            } else {
                mViewModel.saveLoginNull(RequestBodyUtil.getRequestBodyMap(HashMap()))
                if (it.data != null && it.data.status) {
                    SessionUtils.saveSession(it.data.data, context)
                } else {
                    if (!isNetworkConnected(it.status, it.message)) {
                        return@Observer
                    } else {
                        hideKeyboard(mBinding.btLogin, context)
                        showSnackBar(it.data!!.message)
                    }
                }
            }
        })
    }

    /*END LOGIN WEB SERVICE*/


}
