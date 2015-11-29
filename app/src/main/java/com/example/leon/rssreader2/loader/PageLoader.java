package com.example.leon.rssreader2.loader;

import android.content.Context;
import android.content.CursorLoader;

import com.example.leon.rssreader2.content.News;

/**
 * Created by Leon on 27.11.2015.
 */
public class PageLoader extends CursorLoader {

    public PageLoader(Context context, long id) {
        super(context, News.URI, null, News.Columns._ID + "=?", new String[]{String.valueOf(id)}, null);
    }

}
