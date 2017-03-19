package com.jamieadkins.gwent.card.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.jamieadkins.gwent.R;
import com.jamieadkins.gwent.base.BaseActivity;
import com.jamieadkins.gwent.data.interactor.CardsInteractorFirebase;
import com.jamieadkins.gwent.main.MainActivity;

/**
 * Shows card image and details.
 */

public class DetailActivity extends BaseActivity {
    public static final String EXTRA_CARD_ID = "com.jamieadkins.gwent.cardid";
    private DetailContract.Presenter mDetailsPresenter;
    private String mCardId;
    private boolean mFromUrl = false;

    @Override
    public void initialiseContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String action = getIntent().getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            // If Roach was opened from a Gwent DB link.
            mFromUrl = true;

            String uri = getIntent().getData().toString();
            String[] split = uri.split("/");
            mCardId = split[split.length - 1].split("-")[0];
        } else {
            mCardId = getIntent().getStringExtra(EXTRA_CARD_ID);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment;
        if (savedInstanceState == null) {
            fragment = DetailFragment.newInstance(mCardId);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, fragment, fragment.getClass().getSimpleName())
                    .commit();
        } else {
            fragment = getSupportFragmentManager().findFragmentByTag(DetailFragment.class.getSimpleName());
        }

        new DetailPresenter(
                (DetailContract.View) fragment,
                CardsInteractorFirebase.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // If we came from a url, we can open a new Main Activity.
                if (mFromUrl) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Otherwise go back to the Main Activity.
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
