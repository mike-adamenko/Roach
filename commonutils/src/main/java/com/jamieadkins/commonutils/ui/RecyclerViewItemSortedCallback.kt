package com.jamieadkins.commonutils.ui

import android.support.v7.util.SortedList

abstract class RecyclerViewItemSortedCallback(val locale: String) : SortedList.Callback<RecyclerViewItem>() {
    override fun compare(o1: RecyclerViewItem, o2: RecyclerViewItem): Int {
        return o1.compare(o2, locale)
    }

    override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
        return oldItem.isContentsSameAs(newItem, locale)
    }

    override fun areItemsTheSame(item1: RecyclerViewItem, item2: RecyclerViewItem): Boolean {
        return item1.isSameAs(item2)
    }
}