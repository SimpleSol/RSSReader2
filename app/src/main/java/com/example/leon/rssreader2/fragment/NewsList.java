package com.example.leon.rssreader2.fragment;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBar;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.content.News;
import com.example.leon.rssreader2.loader.NewsLoader;
import com.example.leon.rssreader2.widget.NewsListAdapter;

/**
 * Created by Leon on 24.11.2015.
 */
public class NewsList extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter mListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListAdapter = new NewsListAdapter(getActivity());
        setListAdapter(mListAdapter);
        setListShownNoAnimation(false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(R.id.news_loader, getArguments(), this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        final Bundle bundle = new Bundle();
        bundle.putLong(News.Columns._ID, id);
        final NewsPage page = new NewsPage();
        page.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, page)
                .addToBackStack(NewsPage.class.getName())
                .commit();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (R.id.news_loader == id) {
            setListShown(false);
            return new NewsLoader(getActivity().getApplicationContext(), args.getLong(News.Columns.CHANNEL_ID));
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getActivity().getIntent().getStringExtra(Channel.Columns.TITLE));
        }
        if (R.id.news_loader == loader.getId()) {
            mListAdapter.swapCursor(data);
            setListShown(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (R.id.news_loader == loader.getId()) {
            mListAdapter.swapCursor(null);
        }
    }
}

