package com.example.leon.rssreader2.fragment;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.example.leon.rssreader2.content.Channel;

/**
 * Created by Leon on 20.11.2015.
 */
public class ChannelList extends ListFragment implements LoaderManager.LoaderCallbacks<Channel> {


    @Override
    public Loader<Channel> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Channel> loader, Channel data) {

    }

    @Override
    public void onLoaderReset(Loader<Channel> loader) {

    }
}
