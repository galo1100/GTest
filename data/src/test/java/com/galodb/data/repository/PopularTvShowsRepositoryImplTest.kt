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

class PopularTvShowsRepositoryImplTest {

    private lateinit var repository: PopularTvShowsRepositoryImpl

    private val tvShow = TestUtil.createTvShow()

    private val api = mock(GTestApi::class.java)
    private val tvShowMapper = TvShowMapper()

    @Before
    fun setUp() {
        repository = PopularTvShowsRepositoryImpl(api, tvShowMapper)
    }

    @Test
    fun `get popular tv show first page`() {
        val popularTvShowsResponse = TvShowsListResponse(1, 10L, 2, listOf(tvShow))

        `when`(
            api.getPopularTvShows(
                BuildConfig.API_TOKEN,
                1
            )
        ).thenReturn(Single.just(popularTvShowsResponse))

        val response = repository.getPopularTvShows(1)
        response.test()
            .assertValue { it.size == 1 }
            .assertValue { it[0].name == "Batwoman" }
    }

    @Test
    fun `get popular tv show empty page`() {
        val popularTvShowsResponse = TvShowsListResponse(5, 10L, 2, listOf())

        `when`(
            api.getPopularTvShows(
                BuildConfig.API_TOKEN,
                5
            )
        ).thenReturn(Single.just(popularTvShowsResponse))

        val response = repository.getPopularTvShows(5)
        response.test()
            .assertValue { it.isEmpty() }
    }

    @Test
    fun `get popular tv show invalid token`() {
        `when`(
            api.getPopularTvShows(
                BuildConfig.API_TOKEN,
                1
            )
        ).thenReturn(Single.error(ServerException("")))

        val response = repository.getPopularTvShows(1)
        response.test()
            .assertError { (it as? ServerException)?.getErrorMessage().isNullOrBlank() }
    }

    @Test
    fun `get popular tv show no resource`() {
        `when`(
            api.getPopularTvShows(
                BuildConfig.API_TOKEN,
                819289423
            )
        ).thenReturn(Single.error(ServerException("The resource you requested could not be found.")))

        val response = repository.getPopularTvShows(819289423)
        response.test()
            .assertError { !(it as? ServerException)?.getErrorMessage().isNullOrBlank() }
    }

    @Test
    fun `get several popular test 2 pages`() {
        val popularTvShowsResponse = TvShowsListResponse(1, 10L, 2, listOf(tvShow))

        for (i in 1 until 3) {
            `when`(api.getPopularTvShows(BuildConfig.API_TOKEN, i))
                .thenReturn(Single.just(popularTvShowsResponse))
        }

        val response = repository.getSeveralPopularTvShows(1, 2)
        response.test()
            .assertValue { it.size == 2 }
            .assertValue { it.last().popularity == 100f }
    }

    @Test
    fun `get several popular test 8 pages`() {
        val popularTvShowsResponse = TvShowsListResponse(1, 10L, 2, listOf(tvShow))

        for (i in 1 until 9) {
            `when`(api.getPopularTvShows(BuildConfig.API_TOKEN, i))
                .thenReturn(Single.just(popularTvShowsResponse))
        }

        val response = repository.getSeveralPopularTvShows(1, 8)
        response.test()
            .assertValue { it.size == 8 }
            .assertValue { it.last().popularity == 100f }
    }
}
