package com.galodb.gtest.view.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapterBase<out T, V : View>(open val items: List<T>) :
    RecyclerView.Adapter<ViewWrapper<V>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<V> =
        ViewWrapper(onCreateItemView(parent, viewType))

    override fun getItemCount(): Int = items.size

    abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V
}
