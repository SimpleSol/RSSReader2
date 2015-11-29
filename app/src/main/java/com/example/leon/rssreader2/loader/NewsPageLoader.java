package com.example.leon.rssreader2.loader;

import android.content.Context;
import android.content.CursorLoader;

import com.example.leon.rssreader2.content.News;

/**
 * Created by Leon on 29.11.2015.
 */
public class NewsPageLoader extends CursorLoader {

    public NewsPageLoader(Context context, long newsId) {
        super(context, News.URI, null, News.Columns._ID + "=?", new String[]{String.valueOf(newsId)}, null);
    }

}
