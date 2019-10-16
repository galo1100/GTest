package com.galodb.domain.usecase

import com.galodb.domain.executor.PostExecutionThread
import com.galodb.domain.executor.ThreadExecutor
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.repository.SimilarTvShowsRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetSimilarTvShowsUseCaseTest {

    private lateinit var usecase: GetSimilarTvShowsUseCase
    private lateinit var params: GetSimilarTvShowsUseCase.Params
    private lateinit var tvShowModel: TvShowModel

    private val repository = mock(SimilarTvShowsRepository::class.java)
    private val threadExecutor = mock(ThreadExecutor::class.java)
    private val postExecutionThread = mock(PostExecutionThread::class.java)

    @Before
    fun setUp() {
        usecase = GetSimilarTvShowsUseCase(repository, threadExecutor, postExecutionThread)
        params = GetSimilarTvShowsUseCase.Params(1)
        initTvShow()

        `when`(
            postExecutionThread.getScheduler()
        ).thenReturn(mock(Scheduler::class.java))
    }

    @Test
    fun `check use case success`() {
        `when`(
            repository.getSimilarTvShows(1)
        ).thenReturn(Single.just(listOf(tvShowModel, tvShowModel, tvShowModel, tvShowModel)))

        usecase.buildUseCaseSingle(params).test()
            .assertValue { it.size == 4 }
            .assertValue { it[0].voteCount == 500 }
    }

    @Test
    fun `check use case success error`() {
        `when`(
            repository.getSimilarTvShows(1)
        ).thenReturn(Single.error(Exception("No resource found")))

        usecase.buildUseCaseSingle(GetSimilarTvShowsUseCase.Params(1))
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
