package com.galodb.gtest.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.galodb.gtest.R
import com.galodb.gtest.utils.NetworkViewState
import com.galodb.gtest.utils.TvShowSafeCheck
import com.galodb.gtest.utils.extensions.getViewModel
import com.galodb.gtest.utils.extensions.loadImage
import com.galodb.gtest.utils.extensions.observe
import com.galodb.gtest.view.MainActivity
import com.galodb.gtest.view.MainViewModel
import com.galodb.gtest.view.base.BaseFragment
import com.galodb.gtest.view.detail.adapter.SimilarTvShowsAdapter
import kotlinx.android.synthetic.main.fragment_tv_show_detail.*

class TvShowDetailFragment : BaseFragment(), TvShowSafeCheck {

    companion object {
        fun create() = TvShowDetailFragment()
    }

    private val mainActivity: MainActivity?
        get() = activity as? MainActivity

    private val viewModel by lazy {
        getViewModel<MainViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSimilarTvShows()

        initObservers()
        initView()
        initRecycler()
    }

    private fun initObservers() {
        observe(viewModel.similarTvShowsState) { state -> state?.let { onSimilarShowsState(it) } }
    }

    private fun initView() {
        initToolbar()
        setTvShowInfo()
    }

    private fun initToolbar() {
        collapsingToolbar.title = viewModel.currentTvShow?.name

    }

    private fun initRecycler() {
        similarTvShowsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setTvShowInfo() {
        viewModel.currentTvShow?.let { tvShow ->
            header.loadImage(tvShow.backdropPath)
            tvShowDate.text = tvShow.firstAirDate
            tvShowOverview.text = tvShow.overview
            tvShowRate.text = getString(R.string.rate_format, tvShow.voteAverage.toString())
            tvShowVoteCount.text = tvShow.voteCount.toString()
        }
    }

    private fun onSimilarShowsState(state: NetworkViewState) {
        when (state) {
            is NetworkViewState.Loading -> showLoading(true)
            is NetworkViewState.Success<*> -> similarTVShowsSuccess(state.data as? List<*>)
            is NetworkViewState.Error -> similarTVShowsError(state.message)
        }
    }

    private fun similarTVShowsSuccess(list: List<*>?) {
        showLoading(false)

        list?.safeCheck()?.let { popularTvShows ->
            mainActivity?.let { mainActivity ->
                similarTvShowsList.adapter =
                    SimilarTvShowsAdapter(
                        mainActivity,
                        popularTvShows.toMutableList(),
                        mainActivity::onTvShowClicked
                    )
            }
        }

        viewModel.similarTvShowsState.removeObservers(this)
    }

    private fun similarTVShowsError(message: Any) {
        showLoading(false)
        mainActivity?.showError(message)
    }

    override fun onStop() {
        super.onStop()
        viewModel.similarTvShowsState.removeObservers(viewLifecycleOwner)
    }

    private fun showLoading(show: Boolean) {
        if (show) loadingView.visibility = View.VISIBLE
        else loadingView.visibility = View.GONE
    }
}
