package com.example.leon.rssreader2.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.leon.rssreader2.api.RssService;
import com.example.leon.rssreader2.content.Channel;

import retrofit.RestAdapter;

/**
 * Created by Leon on 20.11.2015.
 */
public class RssLoader extends AsyncTaskLoader<Channel> {

    private Channel mChannel;

    public RssLoader(Context context) {
        super(context);
    }

    @Override
    public Channel loadInBackground() {
        return new RestAdapter.Builder().
                setEndpoint("http://news.yandex.ru/")
                .build()
                .create(RssService.class)
                .feed("index.rss")
                .getChannel();
    }

    @Override
    public void deliverResult(Channel data) {
        mChannel = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (mChannel == null) {
            forceLoad();
        } else {
            deliverResult(mChannel);
        }
    }
}
