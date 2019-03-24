package com.sample.myapplication.view

import android.app.Activity
import android.app.Application
import com.sample.myapplication.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var mCalligraphyConfig: CalligraphyConfig

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(mCalligraphyConfig))
                .build()
        )
    }

    override fun activityInjector() = dispatchingAndroidInjector
}