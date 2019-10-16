package com.galodb.gtest.utils

import com.galodb.domain.model.TvShowModel

interface TvShowSafeCheck {
    fun List<*>?.safeCheck() = this?.mapNotNull { it as? TvShowModel }
}
