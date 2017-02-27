package com.jamieadkins.gwent.deck.detail;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jamieadkins.commonutils.ui.RecyclerViewItem;
import com.jamieadkins.gwent.R;
import com.jamieadkins.gwent.card.list.BaseCardViewHolder;
import com.jamieadkins.gwent.data.CardDetails;

import java.util.HashMap;

/**
 * Includes buttons to add and remove cards from a collection.
 */

public class DeckDetailCardViewHolder extends BaseCardViewHolder {
    private Button mAddButton;
    private Button mRemoveButton;
    private TextView mDeckCount;
    private DeckDetailButtonListener mListener;

    public interface DeckDetailButtonListener {
        void addCard(CardDetails card);
        void removeCard(CardDetails card);
    }

    public DeckDetailCardViewHolder(View view, DeckDetailButtonListener listener) {
        super(view);
        mListener = listener;
        mAddButton = (Button) view.findViewById(R.id.deck_add);
        mRemoveButton = (Button) view.findViewById(R.id.deck_remove);
        mDeckCount = (TextView) view.findViewById(R.id.deck_count);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addCard(getBoundCardDetails());
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.removeCard(getBoundCardDetails());
            }
        });
    }

    public void setItemCount(int count) {
        // Hide remove button if there are already 0 cards in collection.
        if (count > 0) {
            mRemoveButton.setVisibility(View.VISIBLE);
        } else {
            mRemoveButton.setVisibility(View.INVISIBLE);
        }

        mDeckCount.setText(String.format(
                mDeckCount.getContext().getString(R.string.in_deck), count));
    }
}