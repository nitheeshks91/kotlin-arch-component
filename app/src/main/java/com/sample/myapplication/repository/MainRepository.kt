package com.sample.myapplication.repository

import android.arch.lifecycle.LiveData
import com.google.gson.JsonObject
import com.sample.myapplication.di.AppExecutors
import com.sample.myapplication.model.remote.ApiResponse
import com.sample.myapplication.model.remote.ApiService
import com.sample.myapplication.model.response.login.LoginResponse
import com.sample.myapplication.network.NetworkBoundNoCacheResource
import com.sample.myapplication.network.helper.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
) : BaseRepository(appExecutors, apiService) {

    fun doLogin(request: JsonObject): LiveData<Resource<LoginResponse>> {
        return object : NetworkBoundNoCacheResource<LoginResponse>() {
            override fun loadFromNetwork(): LiveData<ApiResponse<LoginResponse>> {
                return apiService.login(params = request)
            }
        }.asLiveData()
    }

}