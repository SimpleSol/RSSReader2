package com.example.leon.rssreader2.fragment;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.activity.NewsActivity;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.content.News;
import com.example.leon.rssreader2.loader.ChannelLoader;
import com.example.leon.rssreader2.widget.ChannelListAdapter;

/**
 * Created by Leon on 20.11.2015.
 */
public class ChannelList extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter mListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListAdapter = new ChannelListAdapter(getActivity());
        setListAdapter(mListAdapter);
        setListShownNoAnimation(false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(R.id.rss_loader, Bundle.EMPTY, this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.channels, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_add == item.getItemId()) {
            new AddChannelDialog().show(getFragmentManager(), AddChannelDialog.class.getName());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        final Intent intent = new Intent(getActivity(), NewsActivity.class);
        final Cursor cursor = mListAdapter.getCursor();
        if (cursor.moveToPosition(position)) {
            intent.putExtra(Channel.Columns.TITLE, cursor.getString(cursor.getColumnIndex(Channel.Columns.TITLE)));
        }
        intent.putExtra(News.Columns.CHANNEL_ID, id);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (R.id.rss_loader == id) {
            setListShown(false);
            return new ChannelLoader(getActivity().getApplicationContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (R.id.rss_loader == loader.getId()) {
            mListAdapter.swapCursor(data);
            setListShown(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (R.id.rss_loader == loader.getId()) {
            mListAdapter.swapCursor(null);
        }
    }
}
