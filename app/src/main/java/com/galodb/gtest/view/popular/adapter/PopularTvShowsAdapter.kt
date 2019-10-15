package com.galodb.gtest.view.popular.adapter

import android.content.Context
import android.view.ViewGroup
import com.galodb.domain.model.TvShowModel
import com.galodb.gtest.view.base.adapter.RecyclerViewAdapterBase
import com.galodb.gtest.view.base.adapter.ViewWrapper
import com.galodb.gtest.view.popular.view.PopularTvShowView

class PopularTvShowsAdapter(
    private val context: Context,
    override var items: MutableList<TvShowModel>,
    private val onItemClick: (tvShowModel: TvShowModel) -> Unit
) : RecyclerViewAdapterBase<TvShowModel, PopularTvShowView>(items) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int) = PopularTvShowView(context)

    override fun onBindViewHolder(holder: ViewWrapper<PopularTvShowView>, position: Int) {
        holder.view.bind(items[position])
        holder.view.setOnClickListener { onItemClick(items[position]) }
    }

    fun addItems(newItems: List<TvShowModel>) = items.addAll(newItems)
}
