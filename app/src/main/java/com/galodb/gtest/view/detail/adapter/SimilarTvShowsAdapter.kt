package com.galodb.gtest.view.detail.adapter

import android.content.Context
import android.view.ViewGroup
import com.galodb.domain.model.TvShowModel
import com.galodb.gtest.view.base.adapter.RecyclerViewAdapterBase
import com.galodb.gtest.view.base.adapter.ViewWrapper
import com.galodb.gtest.view.detail.view.SimilarTvShowView

class SimilarTvShowsAdapter(
    private val context: Context,
    override var items: List<TvShowModel>,
    private val onItemClick: (tvShowModel: TvShowModel) -> Unit
) : RecyclerViewAdapterBase<TvShowModel, SimilarTvShowView>(items) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int) = SimilarTvShowView(context)

    override fun onBindViewHolder(holder: ViewWrapper<SimilarTvShowView>, position: Int) {
        holder.view.bind(items[position])
        holder.view.setOnClickListener { onItemClick(items[position]) }
    }
}
