/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smb.copticmatch.data.api

import androidx.lifecycle.LiveData
import com.smb.copticmatch.data.model.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


/**
 * REST API access points
 */
interface WebService {


   /* @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<User>>*/


    @GET("api/speciality_list")

    fun loadUsers(): LiveData<ApiResponse<BaseResponse<List<User>>>>
    @POST("users/login")
    fun login(@Body requestBody: RequestBody): LiveData<ApiResponse<BaseResponse<User>>>

    @POST("logout")
    fun logout(): LiveData<ApiResponse<BaseResponse<User>>>

    @POST("users/registration")
    fun signUp(@Body requestBody: RequestBody): LiveData<ApiResponse<BaseResponse<User>>>

    @POST("users/update_password")
    fun changePassword(@Body requestBody: RequestBody): LiveData<ApiResponse<BaseResponse<User>>>

    @POST("users/save_new_password")
    fun resetPassword(@Body requestBody: RequestBody): LiveData<ApiResponse<BaseResponse<User>>>

    @POST("users/forgot_password")
    fun sentOtp(@Body requestBody: RequestBody): LiveData<ApiResponse<BaseResponse<User>>>


    @POST("users/validate_forgot_password")
    fun verifyOtp(@Body requestBody: RequestBody): LiveData<ApiResponse<BaseResponse<User>>>


}
