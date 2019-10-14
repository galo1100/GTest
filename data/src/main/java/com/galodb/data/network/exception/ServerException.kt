package com.galodb.data.network.exception

class ServerException(
    private val errorMessage: String
) : Exception(errorMessage) {

    fun getErrorMessage() = errorMessage

}
