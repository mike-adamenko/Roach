package com.jamieadkins.gwent.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamieadkins.gwent.R;
import com.jamieadkins.gwent.base.BaseFragment;
import com.jamieadkins.gwent.data.CardDetails;
import com.jamieadkins.gwent.data.interactor.RxDatabaseEvent;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * UI fragment that shows a list of the users decks.
 */

public class CardListFragment extends BaseFragment<CardDetails> implements CardsContract.View {
    private CardsContract.Presenter mCardsPresenter;

    public CardListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRecyclerViewAdapter(new CardRecyclerViewAdapter(CardRecyclerViewAdapter.Detail.LARGE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card_list, container, false);
        setupViews(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        onLoadData();
    }

    @Override
    public void onStop() {
        super.onStop();
        getRecyclerViewAdapter().clear();
        mCardsPresenter.stop();
    }

    @Override
    public void onLoadData() {
        super.onLoadData();
        mCardsPresenter.getMoreCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        setLoading(active);
    }

    @Override
    public void onSearchClosed() {
        setLoadMore(true);
        getRecyclerViewAdapter().clear();
        onLoadData();
    }

    @Override
    public void onSearchResult(ArrayList<CardDetails> searchResults) {
        setLoadMore(false);
        getRecyclerViewAdapter().clear();
        for (CardDetails result : searchResults) {
            getRecyclerViewAdapter().addItem(result);
        }
    }

    @Override
    public void setPresenter(CardsContract.Presenter presenter) {
        mCardsPresenter = presenter;
    }
}