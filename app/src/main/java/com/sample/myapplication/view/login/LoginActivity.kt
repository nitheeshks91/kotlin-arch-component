package com.sample.myapplication.view.login

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.sample.myapplication.BR
import com.sample.myapplication.R
import com.sample.myapplication.databinding.ActivityLoginBinding
import com.sample.myapplication.network.helper.Status
import com.sample.myapplication.view.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val bindingVariable: Int = BR.viewModel

    override val layoutId: Int = R.layout.activity_login

    override val viewModel: Class<LoginViewModel> = LoginViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLoginResponse()
    }

    private fun observeLoginResponse() {
        injectedViewModel.loginResponse.observe(this, Observer {
            it?.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                    }//showLoading{}
                    Status.SUCCESS -> {
                        //dismiss Loading
                        response.data //Loginresponse
                    }
                    Status.ERROR -> {
                        //dismiss Loading
                        // show error message
                    }
                }
            }
        })
    }

}