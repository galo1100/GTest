package com.galodb.gtest.di.module

import com.galodb.data.mapper.TvShowMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun getTvShowMapper() = TvShowMapper()

}
