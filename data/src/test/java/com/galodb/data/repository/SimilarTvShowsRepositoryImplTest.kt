package com.galodb.data.repository

import com.galodb.data.BuildConfig
import com.galodb.data.mapper.TvShowMapper
import com.galodb.data.network.GTestApi
import com.galodb.data.network.entity.response.TvShowsListResponse
import com.galodb.data.network.exception.ServerException
import com.galodb.data.utils.TestUtil
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SimilarTvShowsRepositoryImplTest {

    private lateinit var repository: SimilarTvShowsRepositoryImpl

    private val tvShow = TestUtil.createTvShow()

    private val api = mock(GTestApi::class.java)
    private val tvShowMapper = TvShowMapper()

    @Before
    fun setUp() {
        repository = SimilarTvShowsRepositoryImpl(api, tvShowMapper)
    }

    @Test
    fun `get similar tv show`() {
        val popularTvShowsResponse = TvShowsListResponse(1, 10L, 2, listOf(tvShow))

        `when`(
            api.getSimilarTvShows(
                1,
                BuildConfig.API_TOKEN

            )
        ).thenReturn(Single.just(popularTvShowsResponse))

        val response = repository.getSimilarTvShows(1)
        response.test()
            .assertValue { it.size == 1 }
            .assertValue { it[0].name == "Batwoman" }
    }

    @Test
    fun `get popular tv show empty`() {
        val popularTvShowsResponse = TvShowsListResponse(5, 10L, 2, listOf())

        `when`(
            api.getSimilarTvShows(
                5,
                BuildConfig.API_TOKEN
            )
        ).thenReturn(Single.just(popularTvShowsResponse))

        val response = repository.getSimilarTvShows(5)
        response.test()
            .assertValue { it.isEmpty() }
    }

    @Test
    fun `get popular tv show invalid token`() {
        `when`(
            api.getSimilarTvShows(
                1,
                BuildConfig.API_TOKEN
            )
        ).thenReturn(Single.error(ServerException("")))

        val response = repository.getSimilarTvShows(1)
        response.test()
            .assertError { (it as? ServerException)?.getErrorMessage().isNullOrBlank() }
    }

    @Test
    fun `get popular tv show no resource`() {
        `when`(
            api.getSimilarTvShows(
                819289423,
                BuildConfig.API_TOKEN
            )
        ).thenReturn(Single.error(ServerException("The resource you requested could not be found.")))

        val response = repository.getSimilarTvShows(819289423)
        response.test()
            .assertError { !(it as? ServerException)?.getErrorMessage().isNullOrBlank() }
    }
}
