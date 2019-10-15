package com.galodb.gtest.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.usecase.GetPopularTvShowsUseCase
import com.galodb.gtest.utils.NetworkViewState
import io.reactivex.Single
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.*
import rules.RxSchedulersOverrideRule
import java.io.IOException

class MainViewModelTest {

    @Rule
    @JvmField
    val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private val tvShowModel: TvShowModel = initTvShow()

    private val getPopularTvShows = mock(GetPopularTvShowsUseCase::class.java)

    private val firstCallParams = GetPopularTvShowsUseCase.Params(1, 3)

    private val pageResponse = listOf(tvShowModel, tvShowModel, tvShowModel, tvShowModel)

    @Before
    fun setUp() {
        initTvShow()

        Mockito.`when`(
            getPopularTvShows.execute(firstCallParams)
        ).thenReturn(Single.just(pageResponse))

        mainViewModel = MainViewModel(getPopularTvShows)
    }

    @Test
    fun `initial test get first page success`() {

        verify(getPopularTvShows, times(1)).execute(firstCallParams)
        Assert.assertEquals(mainViewModel.getPopularTvShowsLoading, false)
        Assert.assertEquals(mainViewModel.nextPageToLoad, 4)

        Assert.assertThat(
            mainViewModel.popularTvShowsState.value,
            instanceOf(NetworkViewState.Success::class.java)
        )
    }

    @Test
    fun `test load more pages success`() {
        val params = GetPopularTvShowsUseCase.Params(4, 3)

        Mockito.`when`(
            getPopularTvShows.execute(params)
        ).thenReturn(Single.just(pageResponse))

        mainViewModel.getPopularTvShows()

        verify(getPopularTvShows, times(1)).execute(params)
        Assert.assertEquals(mainViewModel.nextPageToLoad, 7)

        Assert.assertThat(
            mainViewModel.popularTvShowsState.value,
            instanceOf(NetworkViewState.Next::class.java)
        )
    }

    @Test
    fun `test get popular shows error`() {
        val params = GetPopularTvShowsUseCase.Params(4, 3)

        Mockito.`when`(
            getPopularTvShows.execute(params)
        ).thenReturn(Single.error(IOException()))

        mainViewModel.getPopularTvShows()

        verify(getPopularTvShows, times(1)).execute(params)
        Assert.assertEquals(mainViewModel.getPopularTvShowsLoading, false)
        Assert.assertEquals(mainViewModel.nextPageToLoad, 4)

        Assert.assertThat(
            mainViewModel.popularTvShowsState.value,
            instanceOf(NetworkViewState.Error::class.java)
        )
    }

    private fun initTvShow() =
        TvShowModel(
            1,
            "Batwoman",
            "",
            100f,
            "Batwoman",
            listOf(),
            500,
            7.7f,
            listOf(),
            "",
            "",
            "",
            ""
        )
}
