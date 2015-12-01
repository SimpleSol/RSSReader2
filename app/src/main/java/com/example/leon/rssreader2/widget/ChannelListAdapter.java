package com.example.leon.rssreader2.widget;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.view.NewsListItem;
import com.squareup.picasso.Picasso;

/**
 * Created by Leon on 24.11.2015.
 */
public class ChannelListAdapter extends CursorAdapter {

    public ChannelListAdapter(Context context) {
        super(context, null, FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_channel, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((NewsListItem) view).bindCursor(cursor);
    }
}
