package com.smb.copticmatch.ui.login

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.smb.copticmatch.common.AbsentLiveData
import com.smb.copticmatch.data.api.BaseResponse
import com.smb.copticmatch.data.api.Resource
import com.smb.copticmatch.data.model.Otp
import com.smb.copticmatch.data.model.User
import com.smb.copticmatch.repo.UMSRepository
import okhttp3.RequestBody
import javax.inject.Inject

class LoginViewModel
@Inject constructor(repoRepository: UMSRepository) : ViewModel() {

    private val _login = MutableLiveData<String>()
    private val _logout = MutableLiveData<String>()
    private val _sociallogin = MutableLiveData<String>()
    private val _signUp = MutableLiveData<String>()
    private val _updateProfile = MutableLiveData<String>()
    private val _registeredEmailverification = MutableLiveData<String>()
    private val _sendOtp = MutableLiveData<String>()
    private val _verifyOtp = MutableLiveData<String>()
    private val _verifyLinkToken = MutableLiveData<String>()
    private val _setNewPassword = MutableLiveData<String>()
    private val _changePassword = MutableLiveData<String>()
    private val _forgotPassword = MutableLiveData<String>()
    private lateinit var mRequestBody: RequestBody

    val currentPageLiveData = MutableLiveData<Otp>()

    /*START LOGIN*/


    val loginRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations


            .switchMap(_login) { login ->
                if (login == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.login(mRequestBody)
                }
            }

    fun login(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _login.value = "test"
    }

    fun saveLoginNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _login.value = null

    }


    fun retry() {
        _login.value?.let {
            _login.value = it
        }
    }


    /*END LOGIN*/


    /*START LOGOUT*/


    val logoutRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_logout) { login ->
                if (login == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.logout()
                }
            }

    fun logout() {
        _logout.value = "test"
    }

    fun saveLogoutNull() {

        _logout.value = null

    }


    fun retryLogout() {
        _logout.value?.let {
            _logout.value = it
        }
    }



    /*END LOGIN*/






    /*START SIGN UP*/


    val signUpRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_signUp) { signUp ->
                if (signUp == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.signUp(mRequestBody)
                }
            }


    fun saveSignUpNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _signUp.value = null

    }

    fun signUp(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _signUp.value = "test"
    }


    fun retrysignUp() {
        _signUp.value?.let {
            _signUp.value = it
        }
    }


    /*END SIGN UP*/





    /*START UPDATE PROFILE*/


    val updateProfileRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_updateProfile) { signUp ->
                if (signUp == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.updateProfile(mRequestBody)
                }
            }


    fun saveUpdateProfileNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _updateProfile.value = null

    }

    fun updateProfile(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _updateProfile.value = "test"
    }


    fun retryupdateProfile() {
        _updateProfile.value?.let {
            _updateProfile.value = it
        }
    }


    /*END SIGN UP*/



    /*START CHANGE PASSWORD*/


    val changePasswordRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_changePassword) { changePassword ->
                if (changePassword == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.changePassword(mRequestBody)
                }
            }


    fun changePasswordNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _changePassword.value = null

    }

    fun changePassword(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _changePassword.value = "test"
    }


    fun retrychangePassword() {
        _changePassword.value?.let {
            _changePassword.value = it
        }
    }


    /*END CHANGE PASSWORD*/

    /*START RESET PASSWORD*/

    val resetPasswordRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_forgotPassword) { forgotPassword ->
                if (forgotPassword == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.resetPassword(mRequestBody)
                }
            }


    fun resetPasswordNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _forgotPassword.value = null

    }

    fun resetPassword(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _forgotPassword.value = "test"
    }


    fun retryResetPassword() {
        _forgotPassword.value?.let {
            _forgotPassword.value = it
        }
    }


    /*END RESET PASSWORD*/

    /*START SENT OTP*/

    val sendOtpRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_sendOtp) { forgotPassword ->
                if (forgotPassword == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.sentOtp(mRequestBody)
                }
            }


    fun sendOtpNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _sendOtp.value = null

    }

    fun sendOtp(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _sendOtp.value = "test"
    }


    fun retrySendOtp() {
        _sendOtp.value?.let {
            _sendOtp.value = it
        }
    }


    /*END SENT OTP*/

    /*START VERIFY OTP*/

    val verifyOtpRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_verifyOtp) { verifyOtp ->
                if (verifyOtp == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.verifyOtp(mRequestBody)
                }
            }


    fun verifyOtpNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _verifyOtp.value = null

    }

    fun verifyOtp(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _verifyOtp.value = "test"
    }


    fun retryVerifyOtp() {
        _verifyOtp.value?.let {
            _verifyOtp.value = it
        }
    }


    /*END VERIFY OTP*/

    /*START VERIFY LINK TOKEN*/

    val verifyLinkTokenRepositories: LiveData<Resource<BaseResponse<User>>> = Transformations
            .switchMap(_verifyLinkToken) { verifyLinkToken ->
                if (verifyLinkToken == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.verifyLinkToken(mRequestBody)
                }
            }


    fun verifyLinkTokenNull(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _verifyLinkToken.value = null

    }

    fun verifyLinkToken(requestBody: RequestBody?) {
        mRequestBody = requestBody!!
        _verifyLinkToken.value = "test"
    }


    fun retryVerifyLinkToken() {
        _verifyLinkToken.value?.let {
            _verifyLinkToken.value = it
        }
    }


    /*END VERIFY OTP*/



}