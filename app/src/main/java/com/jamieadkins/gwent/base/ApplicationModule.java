package com.jamieadkins.gwent.base;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module.
 */
@Module
public final class ApplicationModule {

    private final Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
