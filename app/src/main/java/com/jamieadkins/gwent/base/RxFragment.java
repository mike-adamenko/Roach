package com.jamieadkins.gwent.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Keeps track of observers so that they can be properly disposed.
 */

public class RxFragment extends Fragment {
    private CompositeDisposable mDisposables;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDisposables.clear();
    }

    public CompositeDisposable getDisposables() {
        return mDisposables;
    }
}
