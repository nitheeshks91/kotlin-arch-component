package com.sample.myapplication.model.remote

import android.arch.lifecycle.LiveData
import com.google.gson.JsonObject
import com.sample.myapplication.model.response.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("")
    fun login(@Body params: JsonObject): LiveData<ApiResponse<LoginResponse>>
}