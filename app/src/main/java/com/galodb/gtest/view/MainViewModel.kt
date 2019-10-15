package com.galodb.gtest.view

import androidx.lifecycle.MutableLiveData
import com.galodb.domain.model.TvShowModel
import com.galodb.domain.usecase.GetPopularTvShowsUseCase
import com.galodb.gtest.utils.NetworkViewState
import com.galodb.gtest.view.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPopularTvShows: GetPopularTvShowsUseCase
) : BaseViewModel() {

    companion object {
        const val INITIAL_PAGE = 1
        const val PAGES_FOR_LOAD = 3
    }

    val popularTvShowsState: MutableLiveData<NetworkViewState> = MutableLiveData()

    var nextPageToLoad = INITIAL_PAGE
    var getPopularTvShowsLoading = false

    init {
        getPopularTvShows()
    }

    fun getPopularTvShows() {
        getPopularTvShowsLoading = true

        getPopularTvShows
            .execute(GetPopularTvShowsUseCase.Params(nextPageToLoad, PAGES_FOR_LOAD))
            .doOnSubscribe { popularTvShowsState.value = NetworkViewState.Loading }
            .subscribeDisposable(
                { tvShows -> getPopularTvShowsSuccess(tvShows) },
                { error -> getPopularTvShowsError(error) }
            )
    }

    private fun getPopularTvShowsSuccess(tvShows: List<TvShowModel>) {
        getPopularTvShowsLoading = false

        if (nextPageToLoad == INITIAL_PAGE) {
            popularTvShowsState.value = NetworkViewState.Success(tvShows)
        } else {
            popularTvShowsState.value = NetworkViewState.Next(tvShows)
        }

        nextPageToLoad += PAGES_FOR_LOAD
    }

    private fun getPopularTvShowsError(error: Any) {
        getPopularTvShowsLoading = false
        popularTvShowsState.value = NetworkViewState.Error(error)
    }
}
