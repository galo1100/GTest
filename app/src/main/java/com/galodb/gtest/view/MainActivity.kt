package com.galodb.gtest.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.galodb.domain.model.TvShowModel
import com.galodb.gtest.R
import com.galodb.gtest.utils.extensions.getViewModel
import com.galodb.gtest.view.base.BaseActivity
import com.galodb.gtest.view.detail.TvShowDetailFragment
import com.galodb.gtest.view.popular.PopularTvShowsFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initView()
    }

    private fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
    }

    private fun initView() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, PopularTvShowsFragment.create())
        fragmentTransaction.commit()
    }

    fun showError(error: Any?) = onError(error)

    fun onTvShowClicked(tvShowModel: TvShowModel) {
        viewModel.currentTvShow = tvShowModel
        replaceSlideFragment(R.id.fragmentContainer, TvShowDetailFragment.create())
    }
}
