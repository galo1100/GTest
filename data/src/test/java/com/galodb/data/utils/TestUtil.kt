package com.galodb.data.utils

import com.galodb.data.network.entity.response.TvShow

object TestUtil {

    fun createTvShow() = TvShow(
        1,
        "Batwoman",
        "",
        100f,
        "Batwoman",
        listOf(),
        500,
        7.7f,
        listOf(),
        "",
        "",
        "",
        ""
    )
}
