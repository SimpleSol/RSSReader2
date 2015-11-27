package com.example.leon.rssreader2.loader;

import android.content.Context;
import android.content.CursorLoader;

import com.example.leon.rssreader2.content.News;

/**
 * Created by Leon on 24.11.2015.
 */
public class NewsLoader extends CursorLoader {

    public NewsLoader(Context context, long channelId) {
        super(context, News.URI, null, News.Columns.CHANNEL_ID + "=?", new String[]{String.valueOf(channelId)}, null);
    }

}
