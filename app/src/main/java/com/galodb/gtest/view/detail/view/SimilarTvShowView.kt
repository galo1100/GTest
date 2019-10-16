package com.galodb.gtest.view.detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.galodb.domain.model.TvShowModel
import com.galodb.gtest.R
import com.galodb.gtest.utils.extensions.loadImage
import kotlinx.android.synthetic.main.item_similar_tv_show.view.*

class SimilarTvShowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_similar_tv_show, this, true)
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun bind(model: TvShowModel) {
        tvShowImage.loadImage(model.posterPath)
    }
}
