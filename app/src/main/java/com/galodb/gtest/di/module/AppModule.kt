package com.galodb.gtest.di.module

import android.content.Context
import com.galodb.gtest.app.GTestApp
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ViewModelModule::class,
        ActivityModule::class,
        RestClientModule::class]
)
class AppModule(val app: GTestApp) {

    @Provides
    fun provideApplication(): GTestApp = app

    @Provides
    fun providesApplicationContext(): Context = app.applicationContext

}