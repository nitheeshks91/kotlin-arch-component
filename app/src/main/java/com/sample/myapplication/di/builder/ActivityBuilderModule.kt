package com.sample.myapplication.di.builder

import com.sample.myapplication.view.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity
}