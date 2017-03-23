package com.jamieadkins.commonutils.ui;

/**
 * Created by jamiea on 25/02/17.
 */

public class Empty implements RecyclerViewItem {
    public static final int TYPE_EMPTY = 10;

    private String mEmptyMessage;
    private String mEmptyAction;
    private int mImageResource;

    public Empty(String message, String action, int image) {
        mEmptyMessage = message;
        mEmptyAction = action;
        mImageResource = image;
    }

    public String getMessage() {
        return mEmptyMessage;
    }

    public String getAction() {
        return mEmptyAction;
    }

    public int getImageResource() {
        return mImageResource;
    }

    @Override
    public int getItemType() {
        return TYPE_EMPTY;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Empty && ((Empty) obj).mEmptyMessage.equals(mEmptyMessage);
    }
}
