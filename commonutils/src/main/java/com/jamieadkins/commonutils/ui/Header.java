package com.jamieadkins.commonutils.ui;

/**
 * Created by jamiea on 25/02/17.
 */

public class Header implements RecyclerViewItem {
    public static final int TYPE_HEADER = 0;

    private String mHeader;
    private String mSubHeader;

    public Header(String header, String subHeader) {
        mHeader = header;
        mSubHeader = subHeader;
    }

    public String getHeader() {
        return mHeader;
    }

    public String getSubHeader() {
        return mSubHeader;
    }

    @Override
    public int getItemType() {
        return TYPE_HEADER;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Header && ((Header) obj).mHeader.equals(mHeader);
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
        if (other instanceof Header) {
            return mHeader.compareTo(((Header) other).getHeader());
        }
        return 0;
    }
}
