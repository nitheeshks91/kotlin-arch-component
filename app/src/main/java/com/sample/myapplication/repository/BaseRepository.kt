package com.sample.myapplication.repository

import com.sample.myapplication.di.AppExecutors
import com.sample.myapplication.model.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class BaseRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
)