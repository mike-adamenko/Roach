package com.jamieadkins.gwent.base;

import android.util.Log;

import com.jamieadkins.gwent.BuildConfig;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by jamiea on 26/02/17.
 */

public abstract class BaseSingleObserver<T> extends DisposableSingleObserver<T> {

    @Override
    public void onError(Throwable e) {
        Log.e(getClass().getSimpleName(), "Observer Error", e);
    }
}
