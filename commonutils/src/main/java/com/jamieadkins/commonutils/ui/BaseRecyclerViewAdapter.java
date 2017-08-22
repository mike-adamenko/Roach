package com.jamieadkins.commonutils.ui;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jamieadkins.commonutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds base logic for recycler view adapters.
 */

public abstract class BaseRecyclerViewAdapter
        extends RecyclerView.Adapter<BaseViewHolder> {
    private SortedList<RecyclerViewItem> mItems;

    public RecyclerViewItem getItemAt(int position) {
        return mItems.get(position);
    }

    public BaseRecyclerViewAdapter() {
        mItems = new SortedList<>(RecyclerViewItem.class, new RecyclerViewItemSortedCallback("en-US") {
            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        } else {
            return mItems.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getItemType();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Header.TYPE_HEADER:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_header, parent, false));
            case SubHeader.TYPE_SUB_HEADER:
                return new SubHeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_subheader, parent, false));
            case GoogleNowSubHeader.TYPE_GOOGLE_NOW_SUB_HEADER:
                return new GoogleNowSubHeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_google_subheader, parent, false));
            default:
                throw new RuntimeException("Detail level has not been implemented.");
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindItem(mItems.get(position));
    }

    public void addItem(RecyclerViewItem item) {
        mItems.add(item);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public SortedList<RecyclerViewItem> getItems() {
        return mItems;
    }
}
