package com.galodb.data.mapper

import com.galodb.data.network.entity.response.TvShow
import com.galodb.data.utils.TestUtil
import com.galodb.domain.model.TvShowModel
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Test

class TvShowMapperTest {

    private val tvShowMapper = TvShowMapper()
    private val tvShow = TestUtil.createTvShow()

    private val tvShowsList = listOf(tvShow, tvShow, tvShow, tvShow, tvShow, tvShow)
    private val emptyList = listOf<TvShow>()

    @Test
    fun `test transform list`() {
        val modelList = tvShowMapper.transformToModel(tvShowsList)
        Assert.assertEquals(6, modelList.size)
        Assert.assertThat(modelList[0], instanceOf(TvShowModel::class.java))
    }

    @Test
    fun `test transform empty list`() {
        val modelList = tvShowMapper.transformToModel(emptyList)
        Assert.assertEquals(0, modelList.size)
    }
}
