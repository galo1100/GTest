package com.galodb.gtest.app

import com.galodb.gtest.di.applyAutoInjector
import com.galodb.gtest.di.components.DaggerAppComponent
import com.galodb.gtest.di.module.AppModule
import dagger.android.DaggerApplication

class GTestApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
    }

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
}
