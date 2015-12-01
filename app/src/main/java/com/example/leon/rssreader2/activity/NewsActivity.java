package com.example.leon.rssreader2.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.fragment.NewsList;

/**
 * Created by Leon on 24.11.2015.
 */
public class NewsActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_news);
        if (savedInstanceState == null) {
            final NewsList newsList = new NewsList();
            newsList.setArguments(getIntent().getExtras());
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame, newsList)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            getFragmentManager().popBackStackImmediate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getFragmentManager().removeOnBackStackChangedListener(this);
    }

    @Override
    public void onBackStackChanged() {
        if (getSupportActionBar() != null) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
    }
}
