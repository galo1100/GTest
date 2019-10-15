package com.galodb.gtest.utils

import com.galodb.domain.exception.MovieDbException
import com.galodb.gtest.R
import java.io.IOException

sealed class NetworkViewState {
    object Loading : NetworkViewState()
    class Success<out T>(val data: T) : NetworkViewState()
    class Next<out T>(val data: T) : NetworkViewState()
    class Error(error: Any) : NetworkViewState() {
        lateinit var message: Any

        init {
            when (error) {
                is MovieDbException -> message = error.getErrorMessage()
                is IOException -> message = R.string.internet_connection_error
                is Throwable -> error.message?.let { message = it }
                else -> message = R.string.generic_error
            }
        }
    }
}
