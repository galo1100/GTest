package com.galodb.gtest.di.module

import com.galodb.data.network.GTestClient
import com.galodb.gtest.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RestClientModule {

    @Provides
    @Singleton
    fun provideNameRestClient() = GTestClient(BuildConfig.API_URL).api

}
