package com.jamieadkins.commonutils.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jamieadkins.commonutils.R;

/**
 * Created by jamiea on 22/03/17.
 */

public class EmptyViewHolder extends BaseViewHolder {
    private Empty mBoundEmpty;

    private final TextView mEmptyText;
    private final Button mEmptyButton;
    private final ImageView mEmptyImage;

    public EmptyViewHolder(View itemView) {
        super(itemView);
        mEmptyText = (TextView) itemView.findViewById(R.id.empty_text);
        mEmptyButton = (Button) itemView.findViewById(R.id.empty_action);
        mEmptyImage = (ImageView) itemView.findViewById(R.id.empty_image);
    }

    @Override
    public void bindItem(RecyclerViewItem item) {
        super.bindItem(item);
        mBoundEmpty = (Empty) item;
        mEmptyText.setText(mBoundEmpty.getMessage());
        mEmptyButton.setText(mBoundEmpty.getAction());
        Glide.with(mEmptyImage.getContext())
                .load(mBoundEmpty.getImageResource())
                .fitCenter()
                .into(mEmptyImage);
    }
}
