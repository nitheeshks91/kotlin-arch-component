package com.sample.myapplication.di.component

import android.app.Application
import com.sample.myapplication.di.builder.ActivityBuilderModule
import com.sample.myapplication.di.module.AppModule
import com.sample.myapplication.di.module.NetworkModule
import com.sample.myapplication.view.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            NetworkModule::class,
            ActivityBuilderModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApplication: BaseApplication)
}