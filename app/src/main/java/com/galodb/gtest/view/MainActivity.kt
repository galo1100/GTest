package com.galodb.gtest.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.galodb.gtest.R
import com.galodb.gtest.utils.extensions.getViewModel
import com.galodb.gtest.view.base.BaseActivity
import com.galodb.gtest.view.popular.PopularTvShowsFragment
import kotlinx.android.synthetic.main.activity_main.*
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

    fun showLoading(show: Boolean) {
        if (show) loadingView.visibility = View.VISIBLE
        else loadingView.visibility = View.GONE
    }

    fun showError(error: Any?) {
        if (loadingView.visibility == View.VISIBLE) showLoading(false)
        onError(error)
    }
}
