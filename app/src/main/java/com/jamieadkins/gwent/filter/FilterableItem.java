package com.jamieadkins.gwent.filter;

import com.jamieadkins.commonutils.ui.RecyclerViewItem;

/**
 * Created by jamiea on 01/03/17.
 */

public class FilterableItem implements RecyclerViewItem {
    private String mId;
    private String mText;
    private boolean mChecked;

    public FilterableItem(String id, String text, boolean checked) {
        mId = id;
        mText = text;
        mChecked = checked;
    }

    @Override
    public String toString() {
        return mText;
    }

    public String getId() {
        return mId;
    }

    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public int getItemType() {
        return FilterRecyclerViewAdapter.TYPE_FILTER;
    }

    @Override
    public boolean isSameAs(RecyclerViewItem other) {
        return equals(other);
    }

    @Override
    public boolean isContentsSameAs(RecyclerViewItem other, String locale) {
        return equals(other);
    }

    @Override
    public int compare(RecyclerViewItem other, String locale) {
        if (other instanceof FilterableItem) {
            return mId.compareTo(((FilterableItem) other).getId());
        }
        return 0;
    }
}
