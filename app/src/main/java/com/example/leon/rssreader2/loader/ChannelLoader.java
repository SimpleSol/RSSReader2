package com.example.leon.rssreader2.loader;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

import com.example.leon.rssreader2.api.RssService;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.content.News;

import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by Leon on 24.11.2015.
 */
public class ChannelLoader extends CursorLoader {

    public ChannelLoader(Context context) {
        super(context, Channel.URI, null, null, null, null);
    }

    @Override
    public Cursor loadInBackground() {
        final Channel channel = new RestAdapter.Builder()
                .setEndpoint("http://www.vesti.ru/")
                .setConverter(new SimpleXMLConverter())
                .build()
                .create(RssService.class)
                .feed("vesti.rss")
                .getChannel();
        final Uri uri = getContext().getContentResolver().insert(Channel.URI, channel.toValues());
        final String channelId = uri.getLastPathSegment();
        for (final News news : channel.getNews()) {
            final ContentValues values = news.toValues();
            values.put(News.Columns.CHANNEL_ID, channelId);
            getContext().getContentResolver().insert(News.URI, values);
        }

        return super.loadInBackground();
    }

}
