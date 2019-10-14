package com.galodb.gtest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.galodb.gtest.R
import com.galodb.gtest.utils.extensions.getViewModel
import com.galodb.gtest.view.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = getViewModel(viewModelFactory)
    }
}
