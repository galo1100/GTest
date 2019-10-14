package com.galodb.gtest.view.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.galodb.gtest.R
import com.galodb.gtest.di.Injectable
import com.galodb.gtest.widget.SingleToast

open class BaseActivity : AppCompatActivity(), Injectable {

    fun onError(error: Any?) = SingleToast.showError(this, error, Toast.LENGTH_SHORT)

    fun replaceSlideFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in, R.anim.slide_out,
            R.anim.slide_in, R.anim.slide_out
        )
        fragmentTransaction.addToBackStack(fragment.javaClass.name)
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }
}
