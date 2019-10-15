package com.galodb.data.network.exception

import com.galodb.domain.exception.MovieDbException

class ServerException(
    private val errorMessage: String
) : Exception(errorMessage), MovieDbException {

    override fun getErrorMessage() = errorMessage

}
