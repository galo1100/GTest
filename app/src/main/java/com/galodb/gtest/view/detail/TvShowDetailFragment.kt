package com.galodb.gtest.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galodb.gtest.R
import com.galodb.gtest.utils.extensions.getViewModel
import com.galodb.gtest.utils.extensions.loadImage
import com.galodb.gtest.view.MainActivity
import com.galodb.gtest.view.MainViewModel
import com.galodb.gtest.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tv_show_detail.*

class TvShowDetailFragment : BaseFragment() {

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

        initView()
    }

    private fun initView() {
        initToolbar()
        setTvShowInfo()
    }

    private fun initToolbar() {
        collapsingToolbar.title = viewModel.currentTvShow?.name

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

}
