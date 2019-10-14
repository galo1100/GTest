package com.galodb.gtest.widget

import android.content.Context
import android.widget.Toast

object SingleToast {

    private var toast: Toast? = null

    fun showError(context: Context, error: Any?, duration: Int) {
        when (error) {
            is String -> show(context, error, duration)
            is Int -> show(context, context.getString(error), duration)
            is Throwable -> show(context, error.message, duration)
        }
    }

    private fun show(context: Context, text: String?, duration: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, text, duration)
        toast?.show()
    }
}
