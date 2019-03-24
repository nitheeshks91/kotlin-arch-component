package com.sample.myapplication.di.module

import android.app.Application
import android.content.Context
import com.sample.myapplication.R
import com.sample.myapplication.model.remote.ApiService
import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideCalligraphyDefaultConfig(application: Application): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/}")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

}