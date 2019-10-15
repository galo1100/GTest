package com.galodb.gtest.view.popular.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.galodb.domain.model.TvShowModel
import com.galodb.gtest.R
import com.galodb.gtest.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_popular_tv_show.view.*

class PopularTvShowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_popular_tv_show, this, true)
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun bind(model: TvShowModel) {
        tvShowImage.loadImage(model.posterPath)

        tvShowName.text = model.name
        tvShowsRate.text = model.voteAverage.toString()
    }
}
