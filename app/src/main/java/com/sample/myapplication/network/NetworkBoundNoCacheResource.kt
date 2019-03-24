package com.sample.myapplication.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.sample.myapplication.model.remote.ApiErrorResponse
import com.sample.myapplication.model.remote.ApiResponse
import com.sample.myapplication.model.remote.ApiSuccessResponse
import com.sample.myapplication.network.helper.Resource
import io.reactivex.annotations.NonNull

abstract class NetworkBoundNoCacheResource<ResultType>  {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {

        val apiResponse = loadFromNetwork()

        result.addSource(apiResponse) {response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    result.setValue(Resource.success(processResponse(response)))
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.setValue(Resource.error(response.error, null))
                }
            }
        }
    }

    @NonNull
    @MainThread
    protected abstract fun loadFromNetwork(): LiveData<ApiResponse<ResultType>>

    protected fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ResultType>) = response.body

}