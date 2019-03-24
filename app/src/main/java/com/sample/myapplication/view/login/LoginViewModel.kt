package com.sample.myapplication.view.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableField
import com.google.gson.JsonObject
import com.sample.myapplication.utils.eventbus.AbsentLiveData
import com.sample.myapplication.model.response.login.LoginResponse
import com.sample.myapplication.network.helper.Resource
import com.sample.myapplication.repository.MainRepository
import com.sample.myapplication.view.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {

    val username: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()

    val usernameError: ObservableField<String> = ObservableField()
    val passwordError: ObservableField<String> = ObservableField()


    private val loginRequest = MutableLiveData<JsonObject>()
    val loginResponse: LiveData<Resource<LoginResponse>> = Transformations
        .switchMap(loginRequest) { request ->
            if (null == request) {
                AbsentLiveData.create()
            } else {
                mainRepository.doLogin(request)
            }
        }

    fun loginClick() {
        if (validateFields())
            doLogin()
    }

    private fun validateFields(): Boolean {
        return true
    }

    private fun doLogin() {
        loginRequest.value = getLoginJsonRequest()
    }

    private fun getLoginJsonRequest(): JsonObject? {
        return JsonObject()
    }
}