package com.galodb.gtest.view.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galodb.domain.model.TvShowModel
import com.galodb.gtest.R
import com.galodb.gtest.utils.NetworkViewState
import com.galodb.gtest.utils.extensions.getViewModel
import com.galodb.gtest.utils.extensions.observe
import com.galodb.gtest.view.MainActivity
import com.galodb.gtest.view.MainViewModel
import com.galodb.gtest.view.base.BaseFragment
import com.galodb.gtest.view.popular.adapter.PopularTvShowsAdapter
import kotlinx.android.synthetic.main.fragment_popular_tv_shows.*


class PopularTvShowsFragment : BaseFragment() {

    companion object {
        fun create() = PopularTvShowsFragment()
        private const val VISIBLE_THRESHOLD = 20
    }

    private val mainActivity: MainActivity?
        get() = activity as? MainActivity

    private val viewModel by lazy {
        getViewModel<MainViewModel>()
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_tv_shows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initRecycler()
    }

    private fun initObservers() {
        observe(viewModel.popularTvShowsState) { state -> state?.let { onPopularShowsState(it) } }
    }

    private fun initRecycler() {
        popularTvShowsList.layoutManager = linearLayoutManager
        popularTvShowsList.addOnScrollListener(scrollListener())
    }

    private fun onPopularShowsState(state: NetworkViewState) {
        when (state) {
            is NetworkViewState.Loading -> mainActivity?.showLoading(true)
            is NetworkViewState.Success<*> -> popularTVShowsSuccess(state.data as? List<*>)
            is NetworkViewState.Next<*> -> loadMore(state.data as? List<*>)
            is NetworkViewState.Error -> popularTVShowsError(state.message)
        }
    }

    private fun popularTVShowsSuccess(list: List<*>?) {
        mainActivity?.showLoading(false)

        list?.safeCheck()?.let { popularTvShows ->
            mainActivity?.let { mainActivity ->
                popularTvShowsList.adapter =
                    PopularTvShowsAdapter(
                        mainActivity,
                        popularTvShows.toMutableList(),
                        ::onTvShowClicked
                    )
            }
        }
    }

    private fun loadMore(list: List<*>?) {
        mainActivity?.showLoading(false)

        list?.safeCheck()?.let { popularTvShows ->
            (popularTvShowsList.adapter as? PopularTvShowsAdapter)?.let { adapter ->
                adapter.addItems(popularTvShows)
                adapter.notifyDataSetChanged()
            }
        }
    }


    private fun onTvShowClicked(tvShowModel: TvShowModel) {
        //TODO
    }

    private fun popularTVShowsError(message: Any) = mainActivity?.showError(message)

    private fun List<*>?.safeCheck() = this?.mapNotNull { it as? TvShowModel }

    private fun scrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItemCount = linearLayoutManager.itemCount
            val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

            if (!viewModel.getPopularTvShowsLoading &&
                totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)
            ) {
                viewModel.getPopularTvShows()
            }
        }
    }
}


