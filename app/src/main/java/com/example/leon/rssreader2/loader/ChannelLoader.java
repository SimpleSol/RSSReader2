package com.example.leon.rssreader2.loader;

import android.content.Context;
import android.content.CursorLoader;

import com.example.leon.rssreader2.content.Channel;

/**
 * Created by Leon on 24.11.2015.
 */
public class ChannelLoader extends CursorLoader {

    public ChannelLoader(Context context) {
        super(context, Channel.URI, null, null, null, null);
    }

}
