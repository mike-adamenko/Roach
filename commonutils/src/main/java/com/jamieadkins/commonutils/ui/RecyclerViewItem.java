package com.jamieadkins.commonutils.ui;

/**
 * Created by jamiea on 25/02/17.
 */

public interface RecyclerViewItem {
    int getItemType();

    boolean isSameAs(RecyclerViewItem other);

    boolean isContentsSameAs(RecyclerViewItem other, String locale);

    int compare(RecyclerViewItem other, String locale);
}
