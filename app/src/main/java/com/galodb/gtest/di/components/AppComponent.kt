package com.galodb.gtest.di.components

import com.galodb.gtest.app.GTestApp
import com.galodb.gtest.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<GTestApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: GTestApp): Builder

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: GTestApp)

}
