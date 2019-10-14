package com.galodb.gtest.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProviders.of(activity as FragmentActivity)[T::class.java]
}
