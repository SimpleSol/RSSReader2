package com.example.leon.rssreader2.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.api.RssService;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.content.News;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by Leon on 30.11.2015.
 */
public class SyncService extends IntentService {

    public SyncService() {
        super("SyncService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final Cursor cursor = getContentResolver().query(Channel.URI, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    syncRssChannel(
                            cursor.getLong(cursor.getColumnIndex(Channel.Columns._ID)),
                            cursor.getString(cursor.getColumnIndex(Channel.Columns.URL))
                    );
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
    }

    private void syncRssChannel(long id, String url) {
        try {
            final Uri uri = Uri.parse(url);
            final Channel channel = new RestAdapter.Builder()
                    .setEndpoint(new Uri.Builder()
                            .scheme(uri.getScheme())
                            .authority(uri.getAuthority())
                            .build()
                            .toString())
                    .setConverter(new SimpleXMLConverter())
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .build()
                    .create(RssService.class)
                    .feed(uri.getLastPathSegment())
                    .getChannel();
            getContentResolver().update(Channel.URI, channel.toValues(), Channel.Columns._ID + "=?",
                    new String[]{String.valueOf(id)});
            for (final News news : channel.getNews()) {
                final ContentValues values = news.toValues();
                values.put(News.Columns.CHANNEL_ID, id);
                getContentResolver().insert(News.URI, values);
            }
        } catch (RetrofitError e) {
            Log.e("SyncService", e.getMessage(), e);
            getContentResolver().delete(Channel.URI, Channel.Columns._ID + "=?",
                    new String[]{String.valueOf(id)});
            Toast.makeText(this, R.string.invalid_channel, Toast.LENGTH_SHORT).show();
        }
    }
}
























