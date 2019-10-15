package com.galodb.domain.usecase

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.Repository
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetPopularTvShowsUseCaseTest {

    private lateinit var usecase: GetPopularTvShowsUseCase
    private lateinit var params: GetPopularTvShowsUseCase.Params
    private lateinit var tvShowModel: TvShowModel

    private val repository = mock(Repository::class.java)
    private val threadExecutor = mock(ThreadExecutor::class.java)
    private val postExecutionThread = mock(PostExecutionThread::class.java)

    @Before
    fun setUp() {
        usecase = GetPopularTvShowsUseCase(repository, threadExecutor, postExecutionThread)
        params = GetPopularTvShowsUseCase.Params(1, 2)
        initTvShow()

        `when`(
            postExecutionThread.getScheduler()
        ).thenReturn(mock(Scheduler::class.java))
    }

    @Test
    fun `check use case success`() {
        `when`(
            repository.getSeveralPopularTvShows(1, 2)
        ).thenReturn(Single.just(listOf(tvShowModel, tvShowModel, tvShowModel, tvShowModel)))

        usecase.buildUseCaseSingle(params).test()
            .assertValue { it.size == 4 }
            .assertValue { it[0].voteCount == 500 }
    }

    @Test
    fun `check use case success error`() {
        `when`(
            repository.getSeveralPopularTvShows(0, 2)
        ).thenReturn(Single.error(Exception("Page should start with 1")))

        usecase.buildUseCaseSingle(GetPopularTvShowsUseCase.Params(0, 2))
            .test()
            .assertError(Exception::class.java)
    }

    private fun initTvShow() {
        tvShowModel = TvShowModel(
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
}
