package com.galodb.gtest.utils

import com.galodb.domain.exception.MovieDbException
import com.galodb.gtest.R
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class NetworkViewStateTest {

    private val loadingState = NetworkViewState.Loading
    private val successState = NetworkViewState.Success(true)
    private val onNextState = NetworkViewState.Next(false)

    @Test
    fun `loading test`() {
        Assert.assertThat(loadingState, instanceOf(NetworkViewState.Loading::class.java))
    }

    @Test
    fun `success test`() {
        Assert.assertEquals(successState.data, true)
    }


    @Test
    fun `on next test`() {
        Assert.assertEquals(onNextState.data, false)
    }

    @Test
    fun `generic error test`() {
        val genericError = NetworkViewState.Error(Any())
        Assert.assertEquals(genericError.message, R.string.generic_error)
    }

    @Test
    fun `internet error test`() {
        val internetError = NetworkViewState.Error(IOException())
        Assert.assertEquals(internetError.message, R.string.internet_connection_error)
    }

    @Test
    fun `throwable error test`() {
        val throwableError = NetworkViewState.Error(Throwable("error 404"))
        Assert.assertEquals(throwableError.message, "error 404")
    }

    @Test
    fun `MovieDbException error test`() {
        val movieDbError = NetworkViewState.Error(MovieDbExceptionTest())
        Assert.assertEquals(movieDbError.message, "TestError")
    }
}


class MovieDbExceptionTest : MovieDbException {
    override fun getErrorMessage() = "TestError"
}
