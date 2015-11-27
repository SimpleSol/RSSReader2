package com.example.leon.rssreader2.content;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.example.leon.rssreader2.sqlite.SQLiteProvider;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Leon on 20.11.2015.
 */
@Root (name = "item", strict = false)
public class News {

    public static final String TABLE = "news";

    public static final Uri URI = Uri.parse("content://" + SQLiteProvider.AUTHORITY + "/" + TABLE);

    @Element(name = "title")
    private String mTitle;

    @Element(name = "link")
    private String mLink;

    @Element(name = "pubDate")
    private String mPubDate;

    @Element(name = "full-text", required = false)
    @Namespace(prefix = "yandex")
    private String mFullText;

    @ElementList(name = "enclosure", inline = true, required = false)
    private List<Enclosure> mEnclosures;

    public ContentValues toValues() {
        final ContentValues values = new ContentValues();
        values.put(Columns.TITLE, mTitle);
        values.put(Columns.LINK, mLink);
        values.put(Columns.FULL_TEXT, mFullText);
        if (mEnclosures != null) {
            for (final Enclosure enclosure : mEnclosures) {
                if (!TextUtils.isEmpty(enclosure.mType)
                        && enclosure.mType.startsWith("image/")) {
                    values.put(Columns.IMAGE_URL, enclosure.mUrl);
                }
            }
        }
        return values;
    }

    public static interface Columns extends BaseColumns {
        String TITLE = "title";
        String LINK = "link";
        String IMAGE_URL = "image_url";
        String FULL_TEXT = "full_text";
        String CHANNEL_ID = "channel_id";
    }

    @Root (strict = false)
    private static class Enclosure {

        @Attribute(name = "type")
        private String mType;

        @Attribute(name = "url")
        private String mUrl;

    }

}
