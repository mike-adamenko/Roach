package com.jamieadkins.commonutils.ui


import android.support.v7.util.SortedList

import java.util.Comparator

class ComparatorBatchedCallback<T>(wrappedCallback: SortedList.Callback<T>) : SortedList.BatchedCallback<T>(wrappedCallback) {
    var comparator: Comparator<T>? = null

    override fun compare(o1: T, o2: T): Int {
        comparator?.let {
            return it.compare(o1, o2)
        }
        return super.compare(o1, o2)
    }
}