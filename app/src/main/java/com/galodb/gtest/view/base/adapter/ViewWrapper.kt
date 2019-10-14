package com.galodb.gtest.view.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewWrapper<out V : View>(val view: V) : RecyclerView.ViewHolder(view)
