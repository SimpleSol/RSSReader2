package com.example.leon.rssreader2.content;

import android.content.ContentValues;
import android.media.Image;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.leon.rssreader2.sqlite.SQLiteProvider;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Collections;
import java.util.List;

/**
 * Created by Leon on 18.11.2015.
 */
@Root(name = "channel", strict = false)
public class Channel {

    public static final String TABLE = "channels";

    public static final Uri URI = Uri.parse("content://" + SQLiteProvider.AUTHORITY + "/" + TABLE);

    @Element(name = "title")
    private String mTitle;

    @Element(name = "link")
    private String mLink;

    @Element(name = "image")
    private Image mImage;

    @ElementList(name = "item", inline = true)
    private List<News> mNews;

    public List<News> getNews() {
        return mNews == null ? Collections.<News>emptyList() : mNews;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public ContentValues toValues() {
        final ContentValues values = new ContentValues();
        values.put(Columns.TITLE, mTitle);
        values.put(Columns.LINK, mLink);
        if (mImage != null) {
            values.put(Columns.IMAGE_URL, mImage.mUrl);
        }
        return values;
    }

    public static interface Columns extends BaseColumns {
        String TITLE = "title";
        String URL = "url";
        String LINK = "link";
        String IMAGE_URL = "image_url";
    }

    @Root(name = "image", strict = false)
    private static class Image {

        @Element(name = "url")
        private String mUrl;

    }

}
